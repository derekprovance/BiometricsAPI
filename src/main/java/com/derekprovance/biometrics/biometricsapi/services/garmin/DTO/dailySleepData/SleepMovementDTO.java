package com.derekprovance.biometrics.biometricsapi.services.garmin.DTO.dailySleepData;

import java.time.LocalDateTime;

public class SleepMovementDTO {
    private LocalDateTime startGMT;
    private LocalDateTime endGMT;
    private Float activityLevel;

    public LocalDateTime getStartGMT() {
        return startGMT;
    }

    public void setStartGMT(LocalDateTime startGMT) {
        this.startGMT = startGMT;
    }

    public LocalDateTime getEndGMT() {
        return endGMT;
    }

    public void setEndGMT(LocalDateTime endGMT) {
        this.endGMT = endGMT;
    }

    public Float getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(Float activityLevel) {
        this.activityLevel = activityLevel;
    }
}
