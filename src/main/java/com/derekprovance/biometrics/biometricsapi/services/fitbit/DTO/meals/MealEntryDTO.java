package com.derekprovance.biometrics.biometricsapi.services.fitbit.DTO.meals;

import java.util.Date;

public class MealEntryDTO {
    private Date logDate;
    private Long logId;
    private LoggedFoodDTO loggedFood;
    private NutritionalValuesDTO nutritionalValues;

    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
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
