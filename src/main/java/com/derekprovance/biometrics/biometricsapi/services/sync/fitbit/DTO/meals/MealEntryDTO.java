package com.derekprovance.biometrics.biometricsapi.services.sync.fitbit.DTO.meals;

import java.time.LocalDate;

public class MealEntryDTO {
    private LocalDate logDate;
    private Long logId;
    private LoggedFoodDTO loggedFood;
    private NutritionalValuesDTO nutritionalValues;

    public LocalDate getLogDate() {
        return logDate;
    }

    public void setLogDate(LocalDate logDate) {
        this.logDate = logDate;
    }

    public LoggedFoodDTO getLoggedFood() {
        return loggedFood;
    }

    public void setLoggedFood(LoggedFoodDTO loggedFood) {
        this.loggedFood = loggedFood;
    }

    public NutritionalValuesDTO getNutritionalValues() {
        return nutritionalValues;
    }

    public void setNutritionalValues(NutritionalValuesDTO nutritionalValues) {
        this.nutritionalValues = nutritionalValues;
    }

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }
}
