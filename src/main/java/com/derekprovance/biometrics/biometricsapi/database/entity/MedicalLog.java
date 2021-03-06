package com.derekprovance.biometrics.biometricsapi.database.entity;

import com.derekprovance.biometrics.biometricsapi.api.psych.medicalLog.Status;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class MedicalLog extends AbstractDateEntity {
    private LocalDate dateChanged;

    private String name;

    private String snoMedCtCode;

    private String icdCode;

    private String notes;

    private Status status;

    @OneToMany(mappedBy = "medicalLog")
    private Set<Symptom> symptoms;

    private LocalDateTime updated = LocalDateTime.now();

    @PreUpdate
    public void setLastUpdate() {  this.updated = LocalDateTime.now(); }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateChanged() {
        return dateChanged;
    }

    public void setDateChanged(LocalDate dateChanged) {
        this.dateChanged = dateChanged;
    }

    public String getSnoMedCtCode() {
        return snoMedCtCode;
    }

    public void setSnoMedCtCode(String snoMedCtCode) {
        this.snoMedCtCode = snoMedCtCode;
    }

    public String getIcdCode() {
        return icdCode;
    }

    public void setIcdCode(String icdCode) {
        this.icdCode = icdCode;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public Set<Symptom> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(Set<Symptom> symptoms) {
        this.symptoms = symptoms;
    }
}
