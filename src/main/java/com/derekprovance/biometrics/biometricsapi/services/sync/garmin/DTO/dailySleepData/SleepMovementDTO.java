package com.derekprovance.biometrics.biometricsapi.services.sync.garmin.DTO.dailySleepData;

import java.sql.Timestamp;

public class SleepMovementDTO {
    private Timestamp startGMT;
    private Timestamp endGMT;
    private Float activityLevel;

    public Timestamp getStartGMT() {
        return startGMT;
    }

    public void setStartGMT(Timestamp startGMT) {
        this.startGMT = startGMT;
    }

    public Timestamp getEndGMT() {
        return endGMT;
    }

    public void setEndGMT(Timestamp endGMT) {
        this.endGMT = endGMT;
    }

    public Float getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(Float activityLevel) {
        this.activityLevel = activityLevel;
    }
}
