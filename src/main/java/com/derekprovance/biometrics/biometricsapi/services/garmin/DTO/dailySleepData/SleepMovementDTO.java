package com.derekprovance.biometrics.biometricsapi.services.garmin.DTO.dailySleepData;

import java.util.Date;

public class SleepMovementDTO {
    private Date startGMT;
    private Date endGMT;
    private Float activityLevel;

    public Date getStartGMT() {
        return startGMT;
    }

    public void setStartGMT(Date startGMT) {
        this.startGMT = startGMT;
    }

    public Date getEndGMT() {
        return endGMT;
    }

    public void setEndGMT(Date endGMT) {
        this.endGMT = endGMT;
    }

    public Float getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(Float activityLevel) {
        this.activityLevel = activityLevel;
    }
}
