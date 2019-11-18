package com.derekprovance.biometrics.biometricsapi.api.utility.garmin;

import java.time.LocalDate;

public class ItemSyncCount {
    private LocalDate date;
    private Integer hrData;
    private Integer movementData;
    private Integer sleepData;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getHrData() {
        return hrData;
    }

    public void setHrData(Integer hrData) {
        this.hrData = hrData;
    }

    public Integer getMovementData() {
        return movementData;
    }

    public void setMovementData(Integer movementData) {
        this.movementData = movementData;
    }

    public Integer getSleepData() {
        return sleepData;
    }

    public void setSleepData(Integer sleepData) {
        this.sleepData = sleepData;
    }
}
