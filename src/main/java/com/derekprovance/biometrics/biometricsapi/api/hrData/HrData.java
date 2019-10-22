package com.derekprovance.biometrics.biometricsapi.api.hrData;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class HrData {
    private @Id @GeneratedValue(strategy= GenerationType.IDENTITY) Integer id;

    private LocalDateTime eventTime;
    private Integer hrValue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getEventTime() {
        return eventTime;
    }

    public void setEventTime(LocalDateTime eventTime) {
        this.eventTime = eventTime;
    }

    public Integer getHrValue() {
        return hrValue;
    }

    public void setHrValue(Integer hrValue) {
        this.hrValue = hrValue;
    }
}
