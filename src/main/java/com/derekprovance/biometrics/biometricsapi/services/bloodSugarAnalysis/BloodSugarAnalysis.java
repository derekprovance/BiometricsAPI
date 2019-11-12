package com.derekprovance.biometrics.biometricsapi.services.bloodSugarAnalysis;

import com.derekprovance.biometrics.biometricsapi.api.physio.bloodSugar.BloodSugar;
import com.derekprovance.biometrics.biometricsapi.api.physio.mealLog.MealBlock;

public class BloodSugarAnalysis {
    private MealBlock lastMeal;
    private MealBlock currentMealTime;
    private HealthStatus diagnosis;
    private BloodSugar bloodSugar;

    public BloodSugarAnalysis(MealBlock lastMeal, MealBlock currentMealTime, HealthStatus diagnosis, BloodSugar bloodSugar) {
        this.lastMeal = lastMeal;
        this.currentMealTime = currentMealTime;
        this.diagnosis = diagnosis;
        this.bloodSugar = bloodSugar;
    }

    public MealBlock getLastMeal() {
        return lastMeal;
    }

    public void setLastMeal(MealBlock lastMeal) {
        this.lastMeal = lastMeal;
    }

    public MealBlock getCurrentMealTime() {
        return currentMealTime;
    }

    public void setCurrentMealTime(MealBlock currentMealTime) {
        this.currentMealTime = currentMealTime;
    }

    public HealthStatus getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(HealthStatus diagnosis) {
        this.diagnosis = diagnosis;
    }

    public BloodSugar getBloodSugar() {
        return bloodSugar;
    }

    public void setBloodSugar(BloodSugar bloodSugar) {
        this.bloodSugar = bloodSugar;
    }
}
