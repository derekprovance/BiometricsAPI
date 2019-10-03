package com.derekprovance.biometrics.biometricsapi.api.waterConsumptionData;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class WaterConsumption {
    private @Id
    @GeneratedValue(strategy= GenerationType.AUTO) Integer id;

    @NotNull @Column(unique=true) @Temporal(TemporalType.DATE)
    private Date date;

    @NotNull
    private Integer amount;

    @NotNull
    private String unit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
