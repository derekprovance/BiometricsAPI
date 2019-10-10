package com.derekprovance.biometrics.biometricsapi.services.garmin.DTO;

import java.util.Date;

public class DailyMovementData {
    private Date calendarDate;
    private Date startTimestampGMT;
    private Date endTimestampGMT;
    private Date startTimestampLocal;
    private Date endTimestampLocal;
    private Object[][] movementValues;

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

    public Object[][] getMovementValues() {
        return movementValues;
    }

    public void setMovementValues(Object[][] movementValues) {
        this.movementValues = movementValues;
    }
}
