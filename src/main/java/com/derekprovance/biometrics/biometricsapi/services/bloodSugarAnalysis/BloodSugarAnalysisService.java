package com.derekprovance.biometrics.biometricsapi.services.bloodSugarAnalysis;

import com.derekprovance.biometrics.biometricsapi.api.physio.bloodSugar.BloodSugar;
import com.derekprovance.biometrics.biometricsapi.api.physio.mealLog.MealBlock;
import com.derekprovance.biometrics.biometricsapi.api.physio.mealLog.MealLogRepository;
import com.derekprovance.biometrics.biometricsapi.services.mealServices.mealBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BloodSugarAnalysisService {

    //Fasting blood sugar (in the morning, before eating): under 100 mg/dL
    //1 hour after a meal: 90 to 130 mg/dL
    //2 hours after a meal: 90 to 110 mg/dL
    //5 or more hours after eating: 70 to 90 mg/dL

    private MealLogRepository mealLogRepository;

    @Autowired
    public BloodSugarAnalysisService(MealLogRepository mealLogRepository) {
        this.mealLogRepository = mealLogRepository;
    }

    public MealBlock analyze(BloodSugar bloodSugar) {
        final MealBlock latestMeal = mealLogRepository.findLatestMeal(bloodSugar.getDatetime().toLocalDate());

        return latestMeal;
    }

}
