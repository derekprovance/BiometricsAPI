package com.derekprovance.biometrics.biometricsapi.api.dataTracking.movementData;

import com.derekprovance.biometrics.biometricsapi.api.rangeEntity.BaseRangeEntity;

import javax.persistence.Entity;

@Entity
public class MovementData extends BaseRangeEntity {
    private Double movement;

    public Double getMovement() {
        return movement;
    }

    public void setMovement(Double movement) {
        this.movement = movement;
    }
}
