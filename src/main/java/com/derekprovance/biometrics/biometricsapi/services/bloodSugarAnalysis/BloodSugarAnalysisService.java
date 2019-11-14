package com.derekprovance.biometrics.biometricsapi.services.bloodSugarAnalysis;

import com.derekprovance.biometrics.biometricsapi.database.entity.BloodSugar;
import com.derekprovance.biometrics.biometricsapi.api.physio.mealLog.MealBlock;
import com.derekprovance.biometrics.biometricsapi.database.repository.MealLogRepositoryGeneric;
import com.derekprovance.biometrics.biometricsapi.services.MealBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BloodSugarAnalysisService {

    private MealLogRepositoryGeneric mealLogRepository;

    @Autowired
    public BloodSugarAnalysisService(MealLogRepositoryGeneric mealLogRepository) {
        this.mealLogRepository = mealLogRepository;
    }

    public BloodSugarAnalysis analyze(BloodSugar bloodSugar) {
        final MealBlock lastMeal = mealLogRepository.findLatestMeal(bloodSugar.getDatetime().toLocalDate());
        MealBlock currentTime = MealBlockService.getMealBlock(bloodSugar.getDatetime());

        if(lastMeal == null) {
            final HealthStatus healthStatus = fastingDifference(bloodSugar.getMgDl());
            return new BloodSugarAnalysis(null, currentTime, healthStatus, bloodSugar);
        }

        int timeDifference = currentTime.getValue() - lastMeal.getValue();
        final HealthStatus healthStatus = determineHealthStatusByMealBlock(timeDifference, bloodSugar.getMgDl());

        return new BloodSugarAnalysis(lastMeal, currentTime, healthStatus, bloodSugar);
    }

    private HealthStatus determineHealthStatusByMealBlock(int timeDifference, int mgDl) {
        HealthStatus result;

        if(timeDifference == 1) {
            result = lastOneHourMealDifference(mgDl);
        } else if (timeDifference == 2) {
            result = lastTwoHourMealDifference(mgDl);
        } else {
            result = fastingDifference(mgDl);
        }

        return result;
    }

    private HealthStatus lastOneHourMealDifference(int mgDl) {
        return (mgDl >= 90 && mgDl <= 130) ? HealthStatus.NORMAL : HealthStatus.ABNORMAL;
    }

    private HealthStatus lastTwoHourMealDifference(int mgDl) {
        return (mgDl >= 90 && mgDl <= 110) ? HealthStatus.NORMAL : HealthStatus.ABNORMAL;
    }

    private HealthStatus fastingDifference(int mgDl) {
        return (mgDl >= 70 && mgDl <= 90) ? HealthStatus.NORMAL : HealthStatus.ABNORMAL;
    }
}
