package com.derekprovance.biometrics.biometricsapi.api.sleepMovement;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class SleepMovement {
    private @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) Integer id;
    private Integer sleepId;
    private LocalDateTime start;
    private LocalDateTime end;
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

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public Float getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(Float activityLevel) {
        this.activityLevel = activityLevel;
    }
}
