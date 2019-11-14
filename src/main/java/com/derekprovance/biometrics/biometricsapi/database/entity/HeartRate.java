package com.derekprovance.biometrics.biometricsapi.database.entity;

import javax.persistence.*;

@Entity
public class HeartRate extends AbstractDateTimeEntity {
    private Integer hrValue;

    public Integer getHrValue() {
        return hrValue;
    }

    public void setHrValue(Integer hrValue) {
        this.hrValue = hrValue;
    }
}
