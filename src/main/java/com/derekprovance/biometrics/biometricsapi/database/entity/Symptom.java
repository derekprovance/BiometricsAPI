package com.derekprovance.biometrics.biometricsapi.database.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Symptom extends AbstractDateTimeEntity {
    private String name;

    private String location;

    private Integer severity;

    private String description;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private MedicalLog medicalLog;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getSeverity() {
        return severity;
    }

    public void setSeverity(Integer severity) {
        this.severity = severity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MedicalLog getMedicalLog() {
        return medicalLog;
    }

    public void setMedicalLog(MedicalLog medicalLog) {
        this.medicalLog = medicalLog;
    }
}
