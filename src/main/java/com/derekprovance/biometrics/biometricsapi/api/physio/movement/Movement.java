package com.derekprovance.biometrics.biometricsapi.api.physio.movement;

import com.derekprovance.biometrics.biometricsapi.api.genericEntities.multiple.BaseRangeEntity;

import javax.persistence.Entity;

@Entity
public class Movement extends BaseRangeEntity {
    private Double movement;

    public Double getMovement() {
        return movement;
    }

    public void setMovement(Double movement) {
        this.movement = movement;
    }
}
