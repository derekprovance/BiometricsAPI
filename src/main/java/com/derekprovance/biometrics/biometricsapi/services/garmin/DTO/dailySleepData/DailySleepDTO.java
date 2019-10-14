package com.derekprovance.biometrics.biometricsapi.services.garmin.DTO.dailySleepData;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class DailySleepDTO {
    private Long id;
    private LocalDate calendarDate;
    private Long sleepTimeSeconds;
    private Long napTimeSeconds;
    private Timestamp sleepStartTimestampGMT;
    private Timestamp sleepEndTimestampGMT;
    private Timestamp sleepStartTimestampLocal;
    private Timestamp autoSleepStartTimestampGMT;
    private Timestamp autoSleepEndTimestampGMT;
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

    public LocalDate getCalendarDate() {
        return calendarDate;
    }

    public void setCalendarDate(LocalDate calendarDate) {
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

    public Timestamp getSleepStartTimestampGMT() {
        return sleepStartTimestampGMT;
    }

    public void setSleepStartTimestampGMT(Timestamp sleepStartTimestampGMT) {
        this.sleepStartTimestampGMT = sleepStartTimestampGMT;
    }

    public Timestamp getSleepEndTimestampGMT() {
        return sleepEndTimestampGMT;
    }

    public void setSleepEndTimestampGMT(Timestamp sleepEndTimestampGMT) {
        this.sleepEndTimestampGMT = sleepEndTimestampGMT;
    }

    public Timestamp getSleepStartTimestampLocal() {
        return sleepStartTimestampLocal;
    }

    public void setSleepStartTimestampLocal(Timestamp sleepStartTimestampLocal) {
        this.sleepStartTimestampLocal = sleepStartTimestampLocal;
    }

    public Timestamp getAutoSleepStartTimestampGMT() {
        return autoSleepStartTimestampGMT;
    }

    public void setAutoSleepStartTimestampGMT(Timestamp autoSleepStartTimestampGMT) {
        this.autoSleepStartTimestampGMT = autoSleepStartTimestampGMT;
    }

    public Timestamp getAutoSleepEndTimestampGMT() {
        return autoSleepEndTimestampGMT;
    }

    public void setAutoSleepEndTimestampGMT(Timestamp autoSleepEndTimestampGMT) {
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
