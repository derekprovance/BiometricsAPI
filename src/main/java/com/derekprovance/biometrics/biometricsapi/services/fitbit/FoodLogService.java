package com.derekprovance.biometrics.biometricsapi.services.fitbit;

import com.derekprovance.biometrics.biometricsapi.api.meals.MealEntry;
import com.derekprovance.biometrics.biometricsapi.api.meals.MealRepository;
import com.derekprovance.biometrics.biometricsapi.services.fitbit.DTO.meals.FitbitFoodEndpointDTO;
import com.derekprovance.biometrics.biometricsapi.services.fitbit.DTO.meals.MealEntryDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FoodLogService {

    private static final Logger log = LoggerFactory.getLogger(FoodLogService.class);

    private MealRepository mealRepository;
    private FitBitAPIService fitbitAPIService;

    @Autowired
    public FoodLogService(MealRepository mealRepository, FitBitAPIService fitbitAPIService) {
        this.mealRepository = mealRepository;
        this.fitbitAPIService = fitbitAPIService;
    }

    public Integer syncWithDatabase() {
        return syncWithDatabase(new Date());
    }

    public Integer syncWithDatabase(Date date) {
        final FitbitFoodEndpointDTO entriesForDate = fitbitAPIService.performFoodApiCall(date);

        if(entriesForDate != null) {
            return processEntries(entriesForDate);
        }

        return 0;
    }

    private Integer processEntries(FitbitFoodEndpointDTO fitbitFoodEndpointDTO) {
        List<MealEntry> mealEntries = new ArrayList<>();

        for(MealEntryDTO mealEntryDTO : fitbitFoodEndpointDTO.getFoods()) {
            if(mealRepository.findByLogId(mealEntryDTO.getLogId()) == null) {
                MealEntry mealEntry = new MealEntry();

                if(mealEntryDTO.getNutritionalValues() != null) {
                    mealEntry.setCarbs(mealEntryDTO.getNutritionalValues().getCarbs());
                    mealEntry.setFat(mealEntryDTO.getNutritionalValues().getFat());
                    mealEntry.setFiber(mealEntryDTO.getNutritionalValues().getFiber());
                    mealEntry.setProtein(mealEntryDTO.getNutritionalValues().getProtein());
                    mealEntry.setSodium(mealEntryDTO.getNutritionalValues().getSodium());
                }

                if(mealEntryDTO.getLoggedFood() != null) {
                    mealEntry.setAmount(mealEntryDTO.getLoggedFood().getAmount());
                    mealEntry.setName(mealEntryDTO.getLoggedFood().getName());
                    mealEntry.setCalories(mealEntryDTO.getLoggedFood().getCalories());
                    mealEntry.setUnit(mealEntryDTO.getLoggedFood().getUnit().getName());
                    mealEntry.setMealTypeId(mealEntryDTO.getLoggedFood().getMealTypeId());
                }

                mealEntry.setDate(mealEntryDTO.getLogDate());
                mealEntry.setLogId(mealEntryDTO.getLogId());

                mealEntries.add(mealEntry);
            }
        }

        if(mealEntries.size() > 0) {
            log.info(String.format("Saving %s food entries to database", mealEntries.size()));
            mealRepository.saveAll(mealEntries);
        }

        return mealEntries.size();
    }

}