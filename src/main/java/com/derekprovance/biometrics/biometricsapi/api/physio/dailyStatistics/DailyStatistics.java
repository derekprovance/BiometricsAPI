package com.derekprovance.biometrics.biometricsapi.api.physio.dailyStatistics;

import com.derekprovance.biometrics.biometricsapi.api.genericEntities.single.BaseSingleEntity;

import javax.persistence.*;

@Entity
public class DailyStatistics extends BaseSingleEntity {
    private Integer maxHr;
    private Integer minHr;
    private Integer restingHr;
    private Integer totalSteps;
    private Integer highlyActiveSeconds;
    private Integer sedentarySeconds;
    private Integer sleepingSeconds;
    private Integer maxStressLevel;
    private Integer lowStressDuration;
    private Integer mediumStressDuration;
    private Integer highStressDuration;

    public Integer getMaxHr() {
        return maxHr;
    }

    public void setMaxHr(Integer maxHr) {
        this.maxHr = maxHr;
    }

    public Integer getMinHr() {
        return minHr;
    }

    public void setMinHr(Integer minHr) {
        this.minHr = minHr;
    }

    public Integer getRestingHr() {
        return restingHr;
    }

    public void setRestingHr(Integer restingHr) {
        this.restingHr = restingHr;
    }

    public Integer getTotalSteps() {
        return totalSteps;
    }

    public void setTotalSteps(Integer totalSteps) {
        this.totalSteps = totalSteps;
    }

    public Integer getHighlyActiveSeconds() {
        return highlyActiveSeconds;
    }

    public void setHighlyActiveSeconds(Integer highlyActiveSeconds) {
        this.highlyActiveSeconds = highlyActiveSeconds;
    }

    public Integer getSedentarySeconds() {
        return sedentarySeconds;
    }

    public void setSedentarySeconds(Integer sedentarySeconds) {
        this.sedentarySeconds = sedentarySeconds;
    }

    public Integer getSleepingSeconds() {
        return sleepingSeconds;
    }

    public void setSleepingSeconds(Integer sleepingSeconds) {
        this.sleepingSeconds = sleepingSeconds;
    }

    public Integer getMaxStressLevel() {
        return maxStressLevel;
    }

    public void setMaxStressLevel(Integer maxStressLevel) {
        this.maxStressLevel = maxStressLevel;
    }

    public Integer getLowStressDuration() {
        return lowStressDuration;
    }

    public void setLowStressDuration(Integer lowStressDuration) {
        this.lowStressDuration = lowStressDuration;
    }

    public Integer getMediumStressDuration() {
        return mediumStressDuration;
    }

    public void setMediumStressDuration(Integer mediumStressDuration) {
        this.mediumStressDuration = mediumStressDuration;
    }

    public Integer getHighStressDuration() {
        return highStressDuration;
    }

    public void setHighStressDuration(Integer highStressDuration) {
        this.highStressDuration = highStressDuration;
    }
}
