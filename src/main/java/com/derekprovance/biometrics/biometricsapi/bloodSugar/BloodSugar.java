package com.derekprovance.biometrics.biometricsapi.bloodSugar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BloodSugar {
    private @Id @GeneratedValue(strategy=GenerationType.AUTO) Integer id;

    private String datetime;

    private Integer mgDl;

    private String notes;

    public Integer getId() {
        return id;
    }

    public String getDatetime() {
        return datetime;
    }

    public Integer getMgDl() {
        return mgDl;
    }

    public String getNotes() {
        return notes;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public void setMgDl(Integer mgDl) {
        this.mgDl = mgDl;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
