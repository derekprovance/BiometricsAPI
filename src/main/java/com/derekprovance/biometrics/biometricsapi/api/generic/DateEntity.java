package com.derekprovance.biometrics.biometricsapi.api.generic;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@MappedSuperclass
public class DateEntity {
    private @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) Integer id;

    @NotNull @Column(unique=true)
    private LocalDate date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
