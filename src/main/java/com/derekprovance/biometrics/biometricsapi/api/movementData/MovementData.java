package com.derekprovance.biometrics.biometricsapi.api.movementData;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class MovementData {
    private @Id
    @GeneratedValue(strategy= GenerationType.AUTO) Integer id;
    private Integer dailyStatisticsId;
    private Date eventTime;
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

    public Date getEventTime() {
        return eventTime;
    }

    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }

    public Float getMovement() {
        return movement;
    }

    public void setMovement(Float movement) {
        this.movement = movement;
    }
}
