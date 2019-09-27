package com.derekprovance.biometrics.biometricsapi.sleepMovement;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class SleepMovement {
    private @Id
    @GeneratedValue(strategy= GenerationType.AUTO) Integer id;
    private Integer sleepId;
    private String start;
    private String end;
    private Float activityLevel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSleepId() {
        return sleepId;
    }

    public void setSleepId(Integer sleepId) {
        this.sleepId = sleepId;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Float getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(Float activityLevel) {
        this.activityLevel = activityLevel;
    }
}
