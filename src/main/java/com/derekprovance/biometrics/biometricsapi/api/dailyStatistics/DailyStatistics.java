package com.derekprovance.biometrics.biometricsapi.api.dailyStatistics;

import javax.persistence.*;
import java.util.Date;

@Entity
public class DailyStatistics {
    private @Id
    @GeneratedValue(strategy= GenerationType.AUTO) Integer id;

    @Temporal(TemporalType.DATE)
    private Date entryDate;
    private Integer maxHr;
    private Integer minHr;
    private Integer restingHr;
    private Integer totalSleep;
    private Integer totalSteps;
    private Integer highlyActiveSeconds;
    private Integer sedentarySeconds;
    private Integer sleepingSeconds;
    private Integer maxStressLevel;
    private Integer lowStressDuration;
    private Integer mediumStressDuration;
    private Integer highStressDuration;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

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

    public Integer getTotalSleep() {
        return totalSleep;
    }

    public void setTotalSleep(Integer totalSleep) {
        this.totalSleep = totalSleep;
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
