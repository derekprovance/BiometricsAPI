package com.derekprovance.biometrics.biometricsapi.services.garmin.DTO.dailySleepData;

public class DailySleepData {
    private DailySleepDTO dailySleepDTO;
    private SleepMovement[] sleepMovement;

    public DailySleepDTO getDailySleepDTO() {
        return dailySleepDTO;
    }

    public void setDailySleepDTO(DailySleepDTO dailySleepDTO) {
        this.dailySleepDTO = dailySleepDTO;
    }

    public SleepMovement[] getSleepMovement() {
        return sleepMovement;
    }

    public void setSleepMovement(SleepMovement[] sleepMovement) {
        this.sleepMovement = sleepMovement;
    }
}
