package com.derekprovance.biometrics.biometricsapi.services.sync.garmin.DTO;

import java.util.Date;

public class DailyHeartRate {
    private Date calendarDate;
    private Date startTimestampGMT;
    private Date endTimestampGMT;
    private Date startTimestampLocal;
    private Date endTimestampLocal;
    private Integer maxHeartRate;
    private Integer minHeartRate;
    private Integer restingHeartRate;
    private Integer lastSevenDaysAvgRestingHeartRate;
    private Object[][] heartRateValues;

    public Date getCalendarDate() {
        return calendarDate;
    }

    public void setCalendarDate(Date calendarDate) {
        this.calendarDate = calendarDate;
    }

    public Date getStartTimestampGMT() {
        return startTimestampGMT;
    }

    public void setStartTimestampGMT(Date startTimestampGMT) {
        this.startTimestampGMT = startTimestampGMT;
    }

    public Date getEndTimestampGMT() {
        return endTimestampGMT;
    }

    public void setEndTimestampGMT(Date endTimestampGMT) {
        this.endTimestampGMT = endTimestampGMT;
    }

    public Date getStartTimestampLocal() {
        return startTimestampLocal;
    }

    public void setStartTimestampLocal(Date startTimestampLocal) {
        this.startTimestampLocal = startTimestampLocal;
    }

    public Date getEndTimestampLocal() {
        return endTimestampLocal;
    }

    public void setEndTimestampLocal(Date endTimestampLocal) {
        this.endTimestampLocal = endTimestampLocal;
    }

    public Integer getMaxHeartRate() {
        return maxHeartRate;
    }

    public void setMaxHeartRate(Integer maxHeartRate) {
        this.maxHeartRate = maxHeartRate;
    }

    public Integer getMinHeartRate() {
        return minHeartRate;
    }

    public void setMinHeartRate(Integer minHeartRate) {
        this.minHeartRate = minHeartRate;
    }

    public Integer getRestingHeartRate() {
        return restingHeartRate;
    }

    public void setRestingHeartRate(Integer restingHeartRate) {
        this.restingHeartRate = restingHeartRate;
    }

    public Integer getLastSevenDaysAvgRestingHeartRate() {
        return lastSevenDaysAvgRestingHeartRate;
    }

    public void setLastSevenDaysAvgRestingHeartRate(Integer lastSevenDaysAvgRestingHeartRate) {
        this.lastSevenDaysAvgRestingHeartRate = lastSevenDaysAvgRestingHeartRate;
    }

    public Object[][] getHeartRateValues() {
        return heartRateValues;
    }

    public void setHeartRateValues(Object[][] heartRateValues) {
        this.heartRateValues = heartRateValues;
    }
}
