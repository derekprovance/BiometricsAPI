package com.derekprovance.biometrics.biometricsapi.services.sync;

import com.derekprovance.biometrics.biometricsapi.api.physio.mealLog.MealBlock;
import com.derekprovance.biometrics.biometricsapi.api.physio.mealLog.MealLog;
import com.derekprovance.biometrics.biometricsapi.api.physio.mealLog.MealLogRepository;
import com.derekprovance.biometrics.biometricsapi.services.fitbit.DTO.meals.FitbitFoodEndpointDTO;
import com.derekprovance.biometrics.biometricsapi.services.fitbit.DTO.meals.MealEntryDTO;
import com.derekprovance.biometrics.biometricsapi.services.fitbit.FitBitAPIService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class FoodLogService {

    private static final Logger log = LoggerFactory.getLogger(FoodLogService.class);

    private final MealLogRepository mealLogRepository;
    private final FitBitAPIService fitbitAPIService;

    @Autowired
    public FoodLogService(MealLogRepository mealLogRepository, FitBitAPIService fitbitAPIService) {
        this.mealLogRepository = mealLogRepository;
        this.fitbitAPIService = fitbitAPIService;
    }

    public void syncWithDatabase() throws CredentialException {
        syncWithDatabase(LocalDate.now());
    }

    public Integer syncWithDatabase(LocalDate date) throws CredentialException {
        final FitbitFoodEndpointDTO entriesForDate = fitbitAPIService.performFoodApiCall(date);

        if(entriesForDate != null) {
            return processEntries(entriesForDate);
        }

        return 0;
    }

    private Integer processEntries(FitbitFoodEndpointDTO fitbitFoodEndpointDTO) {
        List<MealLog> mealEntries = new ArrayList<>();

        for(MealEntryDTO mealEntryDTO : fitbitFoodEndpointDTO.getFoods()) {
            if(mealLogRepository.findByLogId(mealEntryDTO.getLogId()) == null) {
                MealLog mealLog = new MealLog();

                if(mealEntryDTO.getNutritionalValues() != null) {
                    mealLog.setCarbs(mealEntryDTO.getNutritionalValues().getCarbs());
                    mealLog.setFat(mealEntryDTO.getNutritionalValues().getFat());
                    mealLog.setFiber(mealEntryDTO.getNutritionalValues().getFiber());
                    mealLog.setProtein(mealEntryDTO.getNutritionalValues().getProtein());
                    mealLog.setSodium(mealEntryDTO.getNutritionalValues().getSodium());
                }

                if(mealEntryDTO.getLoggedFood() != null) {
                    mealLog.setAmount(mealEntryDTO.getLoggedFood().getAmount());
                    mealLog.setName(mealEntryDTO.getLoggedFood().getName());
                    mealLog.setCalories(mealEntryDTO.getLoggedFood().getCalories());
                    mealLog.setUnit(mealEntryDTO.getLoggedFood().getUnit().getName());
                    mealLog.setMealBlock(MealBlock.valueOf(mealEntryDTO.getLoggedFood().getMealTypeId()));
                }

                mealLog.setDate(mealEntryDTO.getLogDate());
                mealLog.setLogId(mealEntryDTO.getLogId());

                mealEntries.add(mealLog);
            }
        }

        if(mealEntries.size() > 0) {
            log.info(String.format("Saving %s food entries to database", mealEntries.size()));
            mealLogRepository.saveAll(mealEntries);
        }

        return mealEntries.size();
    }

}