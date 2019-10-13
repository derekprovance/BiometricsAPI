package com.derekprovance.biometrics.biometricsapi.services.garmin.DTO.dailySleepData;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DailySleepDTO {
    private Long id;
    private LocalDate calendarDate;
    private Long sleepTimeSeconds;
    private Long napTimeSeconds;
    private LocalDateTime sleepStartTimestampGMT;
    private LocalDateTime sleepEndTimestampGMT;
    private LocalDateTime sleepStartTimestampLocal;
    private LocalDateTime autoSleepStartTimestampGMT;
    private LocalDateTime autoSleepEndTimestampGMT;
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

    public LocalDateTime getSleepStartTimestampGMT() {
        return sleepStartTimestampGMT;
    }

    public void setSleepStartTimestampGMT(LocalDateTime sleepStartTimestampGMT) {
        this.sleepStartTimestampGMT = sleepStartTimestampGMT;
    }

    public LocalDateTime getSleepEndTimestampGMT() {
        return sleepEndTimestampGMT;
    }

    public void setSleepEndTimestampGMT(LocalDateTime sleepEndTimestampGMT) {
        this.sleepEndTimestampGMT = sleepEndTimestampGMT;
    }

    public LocalDateTime getSleepStartTimestampLocal() {
        return sleepStartTimestampLocal;
    }

    public void setSleepStartTimestampLocal(LocalDateTime sleepStartTimestampLocal) {
        this.sleepStartTimestampLocal = sleepStartTimestampLocal;
    }

    public LocalDateTime getAutoSleepStartTimestampGMT() {
        return autoSleepStartTimestampGMT;
    }

    public void setAutoSleepStartTimestampGMT(LocalDateTime autoSleepStartTimestampGMT) {
        this.autoSleepStartTimestampGMT = autoSleepStartTimestampGMT;
    }

    public LocalDateTime getAutoSleepEndTimestampGMT() {
        return autoSleepEndTimestampGMT;
    }

    public void setAutoSleepEndTimestampGMT(LocalDateTime autoSleepEndTimestampGMT) {
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
