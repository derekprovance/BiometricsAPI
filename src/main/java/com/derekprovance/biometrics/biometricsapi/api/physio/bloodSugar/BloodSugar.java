package com.derekprovance.biometrics.biometricsapi.api.physio.bloodSugar;

import com.derekprovance.biometrics.biometricsapi.api.genericEntities.range.BaseRangeEntity;

import javax.persistence.Entity;

@Entity
public class BloodSugar extends BaseRangeEntity {
    private Integer mgDl;
    private String notes;

    public Integer getMgDl() {
        return mgDl;
    }

    public void setMgDl(Integer mgDl) {
        this.mgDl = mgDl;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
