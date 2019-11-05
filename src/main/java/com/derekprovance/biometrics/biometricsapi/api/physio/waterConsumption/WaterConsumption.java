package com.derekprovance.biometrics.biometricsapi.api.physio.waterConsumption;

import com.derekprovance.biometrics.biometricsapi.api.genericEntities.single.BaseSingleEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class WaterConsumption extends BaseSingleEntity {
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
