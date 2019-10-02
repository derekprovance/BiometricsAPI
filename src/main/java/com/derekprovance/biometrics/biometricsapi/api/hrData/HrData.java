package com.derekprovance.biometrics.biometricsapi.api.hrData;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class HrData {
    private @Id
    @GeneratedValue(strategy= GenerationType.AUTO) Integer id;
    private Integer DailyStatisticsId;
    private String eventTime;
    private Integer hrValue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDailyStatisticsId() {
        return DailyStatisticsId;
    }

    public void setDailyStatisticsId(Integer dailyStatisticsId) {
        DailyStatisticsId = dailyStatisticsId;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public Integer getHrValue() {
        return hrValue;
    }

    public void setHrValue(Integer hrValue) {
        this.hrValue = hrValue;
    }
}
