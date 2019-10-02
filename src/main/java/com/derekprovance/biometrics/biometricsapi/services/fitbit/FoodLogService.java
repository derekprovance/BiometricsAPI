package com.derekprovance.biometrics.biometricsapi.services.fitbit;

import com.derekprovance.biometrics.biometricsapi.api.meals.MealRepository;
import com.derekprovance.biometrics.biometricsapi.services.fitbit.DTO.meals.FitbitFoodEndpointDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class FoodLogService {

    @Autowired
    MealRepository mealRepository;

    @Autowired
    FitbitFoodAPIService fitbitFoodAPIService;

    public void syncWithDatabase() {
        syncWithDatabase(new Date());
    }

    public void syncWithDatabase(Date date) {
        final FitbitFoodEndpointDTO entriesForDate = fitbitFoodAPIService.getEntriesForDate(date);
    }

}
