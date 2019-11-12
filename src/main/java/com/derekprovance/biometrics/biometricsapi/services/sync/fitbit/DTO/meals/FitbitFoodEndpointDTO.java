package com.derekprovance.biometrics.biometricsapi.services.sync.fitbit.DTO.meals;

public class FitbitFoodEndpointDTO {
    private MealEntryDTO[] foods;
    private SummaryDTO summary;

    public MealEntryDTO[] getFoods() {
        return foods;
    }

    public void setFoods(MealEntryDTO[] foods) {
        this.foods = foods;
    }

    public SummaryDTO getSummary() {
        return summary;
    }

    public void setSummary(SummaryDTO summary) {
        this.summary = summary;
    }
}
