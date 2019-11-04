package com.derekprovance.biometrics.biometricsapi.api.dataTracking.hrData;

import com.derekprovance.biometrics.biometricsapi.api.rangeEntity.BaseRangeEntity;

import javax.persistence.*;

@Entity
public class HrData extends BaseRangeEntity {
    private Integer hrValue;

    public Integer getHrValue() {
        return hrValue;
    }

    public void setHrValue(Integer hrValue) {
        this.hrValue = hrValue;
    }
}
