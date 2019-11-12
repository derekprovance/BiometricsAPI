package com.derekprovance.biometrics.biometricsapi.services.sync.garmin.DTO.dailySleepData;

public class DailySleepData {
    private DailySleepDTO dailySleepDTO;
    private SleepMovementDTO[] sleepMovement;

    public DailySleepDTO getDailySleepDTO() {
        return dailySleepDTO;
    }

    public void setDailySleepDTO(DailySleepDTO dailySleepDTO) {
        this.dailySleepDTO = dailySleepDTO;
    }

    public SleepMovementDTO[] getSleepMovement() {
        return sleepMovement;
    }

    public void setSleepMovement(SleepMovementDTO[] sleepMovement) {
        this.sleepMovement = sleepMovement;
    }
}
