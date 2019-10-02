package com.derekprovance.biometrics.biometricsapi.services.fitbit.DTO.meals;

public class MealEntryDTO {
    private String logDate;
    private LoggedFoodDTO loggedFood;
    private NutritionalValuesDTO nutritionalValues;

    public String getLogDate() {
        return logDate;
    }

    public void setLogDate(String logDate) {
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
}
