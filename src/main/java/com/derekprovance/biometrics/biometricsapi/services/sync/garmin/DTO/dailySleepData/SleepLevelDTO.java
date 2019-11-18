package com.derekprovance.biometrics.biometricsapi.services.sync.garmin.DTO.dailySleepData;

import java.sql.Timestamp;

public class SleepLevelDTO {
    private Timestamp startGMT;
    private Timestamp endGMT;
    private Integer activityLevel;

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

    public Integer getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(Integer activityLevel) {
        this.activityLevel = activityLevel;
    }
}
