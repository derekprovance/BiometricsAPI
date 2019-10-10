package com.derekprovance.biometrics.biometricsapi.services.garmin.DTO.dailySleepData;

import java.util.Date;

public class DailySleepDTO {
    private Long id;
    private Date calendarDate;
    private Long sleepTimeSeconds;
    private Long napTimeSeconds;
    private Date sleepStartTimestampGMT;
    private Date sleepEndTimestampGMT;
    private Date sleepStartTimestampLocal;
    private Date autoSleepStartTimestampGMT;
    private Date autoSleepEndTimestampGMT;
    private Long unmeasurableSleepSeconds;
    private Long deepSleepSeconds;
    private Long lightSleepSeconds;
    private Long remSleepSeconds;
    private Long awakeSleepSeconds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCalendarDate() {
        return calendarDate;
    }

    public void setCalendarDate(Date calendarDate) {
        this.calendarDate = calendarDate;
    }

    public Long getSleepTimeSeconds() {
        return sleepTimeSeconds;
    }

    public void setSleepTimeSeconds(Long sleepTimeSeconds) {
        this.sleepTimeSeconds = sleepTimeSeconds;
    }

    public Long getNapTimeSeconds() {
        return napTimeSeconds;
    }

    public void setNapTimeSeconds(Long napTimeSeconds) {
        this.napTimeSeconds = napTimeSeconds;
    }

    public Date getSleepStartTimestampGMT() {
        return sleepStartTimestampGMT;
    }

    public void setSleepStartTimestampGMT(Date sleepStartTimestampGMT) {
        this.sleepStartTimestampGMT = sleepStartTimestampGMT;
    }

    public Date getSleepEndTimestampGMT() {
        return sleepEndTimestampGMT;
    }

    public void setSleepEndTimestampGMT(Date sleepEndTimestampGMT) {
        this.sleepEndTimestampGMT = sleepEndTimestampGMT;
    }

    public Date getSleepStartTimestampLocal() {
        return sleepStartTimestampLocal;
    }

    public void setSleepStartTimestampLocal(Date sleepStartTimestampLocal) {
        this.sleepStartTimestampLocal = sleepStartTimestampLocal;
    }

    public Date getAutoSleepStartTimestampGMT() {
        return autoSleepStartTimestampGMT;
    }

    public void setAutoSleepStartTimestampGMT(Date autoSleepStartTimestampGMT) {
        this.autoSleepStartTimestampGMT = autoSleepStartTimestampGMT;
    }

    public Date getAutoSleepEndTimestampGMT() {
        return autoSleepEndTimestampGMT;
    }

    public void setAutoSleepEndTimestampGMT(Date autoSleepEndTimestampGMT) {
        this.autoSleepEndTimestampGMT = autoSleepEndTimestampGMT;
    }

    public Long getUnmeasurableSleepSeconds() {
        return unmeasurableSleepSeconds;
    }

    public void setUnmeasurableSleepSeconds(Long unmeasurableSleepSeconds) {
        this.unmeasurableSleepSeconds = unmeasurableSleepSeconds;
    }

    public Long getDeepSleepSeconds() {
        return deepSleepSeconds;
    }

    public void setDeepSleepSeconds(Long deepSleepSeconds) {
        this.deepSleepSeconds = deepSleepSeconds;
    }

    public Long getLightSleepSeconds() {
        return lightSleepSeconds;
    }

    public void setLightSleepSeconds(Long lightSleepSeconds) {
        this.lightSleepSeconds = lightSleepSeconds;
    }

    public Long getRemSleepSeconds() {
        return remSleepSeconds;
    }

    public void setRemSleepSeconds(Long remSleepSeconds) {
        this.remSleepSeconds = remSleepSeconds;
    }

    public Long getAwakeSleepSeconds() {
        return awakeSleepSeconds;
    }

    public void setAwakeSleepSeconds(Long awakeSleepSeconds) {
        this.awakeSleepSeconds = awakeSleepSeconds;
    }
}
