package com.derekprovance.biometrics.biometricsapi.api.physio.movement;

import com.derekprovance.biometrics.biometricsapi.api.genericEntities.DateTimeEntity;

import javax.persistence.Entity;

@Entity
public class Movement extends DateTimeEntity {
    private Double movement;

    public Double getMovement() {
        return movement;
    }

    public void setMovement(Double movement) {
        this.movement = movement;
    }
}
