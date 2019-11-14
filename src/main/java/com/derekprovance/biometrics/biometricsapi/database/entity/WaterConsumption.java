package com.derekprovance.biometrics.biometricsapi.database.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class WaterConsumption extends AbstractDateEntity {
    @NotNull
    private Integer amount;

    @NotNull
    private String unit;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
