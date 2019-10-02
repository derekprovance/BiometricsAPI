package com.derekprovance.biometrics.biometricsapi.movementData;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MovementData {
    private @Id
    @GeneratedValue(strategy= GenerationType.AUTO) Integer id;
    private Integer dailyStatisticsId;
    private String eventTime;
    private Float movement;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDailyStatisticsId() {
        return dailyStatisticsId;
    }

    public void setDailyStatisticsId(Integer dailyStatisticsId) {
        this.dailyStatisticsId = dailyStatisticsId;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public Float getMovement() {
        return movement;
    }

    public void setMovement(Float movement) {
        this.movement = movement;
    }
}
