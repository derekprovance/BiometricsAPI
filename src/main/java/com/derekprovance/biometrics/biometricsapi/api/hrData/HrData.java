package com.derekprovance.biometrics.biometricsapi.api.hrData;

import javax.persistence.*;
import java.util.Date;

@Entity
public class HrData {
    private @Id
    @GeneratedValue(strategy= GenerationType.AUTO) Integer id;
    private Integer DailyStatisticsId;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date eventTime;
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

    public Date getEventTime() {
        return eventTime;
    }

    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }

    public Integer getHrValue() {
        return hrValue;
    }

    public void setHrValue(Integer hrValue) {
        this.hrValue = hrValue;
    }
}
