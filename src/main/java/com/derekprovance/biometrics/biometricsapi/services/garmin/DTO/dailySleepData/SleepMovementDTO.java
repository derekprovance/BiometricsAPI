package com.derekprovance.biometrics.biometricsapi.services.garmin.DTO.dailySleepData;

import java.sql.Timestamp;
import java.time.LocalDateTime;

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
