package com.derekprovance.biometrics.biometricsapi.api.physio.heartRate;

import com.derekprovance.biometrics.biometricsapi.api.generic.DateTimeEntity;

import javax.persistence.*;

@Entity
public class HeartRate extends DateTimeEntity {
    private Integer hrValue;

    public Integer getHrValue() {
        return hrValue;
    }

    public void setHrValue(Integer hrValue) {
        this.hrValue = hrValue;
    }
}
