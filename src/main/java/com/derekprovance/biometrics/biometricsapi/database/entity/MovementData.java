package com.derekprovance.biometrics.biometricsapi.database.entity;

import javax.persistence.Entity;

@Entity
public class MovementData extends AbstractDateTimeEntity {
    private Double movement;

    public Double getMovement() {
        return movement;
    }

    public void setMovement(Double movement) {
        this.movement = movement;
    }
}
