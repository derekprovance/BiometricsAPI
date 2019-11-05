package com.derekprovance.biometrics.biometricsapi.api.psych.medicalLog;

import com.derekprovance.biometrics.biometricsapi.api.genericEntities.single.BaseSingleEntity;

import javax.persistence.Entity;
import javax.persistence.PreUpdate;
import java.time.LocalDate;
import java.util.ArrayList;

@Entity
public class MedicalLog extends BaseSingleEntity {
    private LocalDate dateChanged;
    private String bodyLocation;
    private ArrayList<String> symptoms;
    private String snoMedCtCode;
    private String icdCode;
    private String notes;
    private Status status;
    private LocalDate updated = LocalDate.now();

    @PreUpdate
    public void setLastUpdate() {  this.updated = LocalDate.now(); }

    public LocalDate getDateChanged() {
        return dateChanged;
    }

    public void setDateChanged(LocalDate dateChanged) {
        this.dateChanged = dateChanged;
    }

    public String getBodyLocation() {
        return bodyLocation;
    }

    public void setBodyLocation(String bodyLocation) {
        this.bodyLocation = bodyLocation;
    }

    public ArrayList<String> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(ArrayList<String> symptoms) {
        this.symptoms = symptoms;
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

    public LocalDate getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDate updated) {
        this.updated = updated;
    }
}
