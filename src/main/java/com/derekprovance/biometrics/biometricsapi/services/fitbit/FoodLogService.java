package com.derekprovance.biometrics.biometricsapi.services.fitbit;

import com.derekprovance.biometrics.biometricsapi.api.meals.MealEntry;
import com.derekprovance.biometrics.biometricsapi.api.meals.MealRepository;
import com.derekprovance.biometrics.biometricsapi.services.fitbit.DTO.meals.FitbitFoodEndpointDTO;
import com.derekprovance.biometrics.biometricsapi.services.fitbit.DTO.meals.MealEntryDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FoodLogService {

    private static final Logger log = LoggerFactory.getLogger(FoodLogService.class);

    @Autowired
    MealRepository mealRepository;

    @Autowired
    FitbitFoodAPIService fitbitFoodAPIService;

    @Scheduled(fixedRate = 3600000)
    public void syncWithDatabase() {
        syncWithDatabase(new Date());
    }

    public void syncWithDatabase(Date date) {
        final FitbitFoodEndpointDTO entriesForDate = fitbitFoodAPIService.getEntriesForDate(date);

        if(entriesForDate != null) {
            processEntries(entriesForDate);
        }
    }

    private void processEntries(FitbitFoodEndpointDTO fitbitFoodEndpointDTO) {
        List<MealEntry> mealEntries = new ArrayList<>();

        for(MealEntryDTO mealEntryDTO : fitbitFoodEndpointDTO.getFoods()) {
            if(mealRepository.findByLogId(mealEntryDTO.getLogId()) == null) {
                MealEntry mealEntry = new MealEntry();
                mealEntry.setAmount(mealEntryDTO.getLoggedFood().getAmount());
                mealEntry.setName(mealEntryDTO.getLoggedFood().getName());
                mealEntry.setCalories(mealEntryDTO.getLoggedFood().getCalories());
                mealEntry.setCarbs(mealEntryDTO.getNutritionalValues().getCarbs());
                mealEntry.setDate(mealEntryDTO.getLogDate());
                mealEntry.setFat(mealEntryDTO.getNutritionalValues().getFat());
                mealEntry.setFiber(mealEntryDTO.getNutritionalValues().getFiber());
                mealEntry.setProtein(mealEntryDTO.getNutritionalValues().getProtein());
                mealEntry.setSodium(mealEntryDTO.getNutritionalValues().getSodium());
                mealEntry.setUnit(mealEntryDTO.getLoggedFood().getUnit().getName());
                mealEntry.setLogId(mealEntryDTO.getLogId());
                mealEntry.setMealTypeId(mealEntryDTO.getLoggedFood().getMealTypeId());

                mealEntries.add(mealEntry);
            }
        }

        if(mealEntries.size() > 0) {
            log.info(String.format("Saving %s food entries to database", mealEntries.size()));
            mealRepository.saveAll(mealEntries);
        }
    }

}