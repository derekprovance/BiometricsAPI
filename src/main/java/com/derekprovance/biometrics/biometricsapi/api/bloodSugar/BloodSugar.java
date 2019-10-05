package com.derekprovance.biometrics.biometricsapi.api.bloodSugar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class BloodSugar {
    private @Id @GeneratedValue(strategy=GenerationType.AUTO) Integer id;
    private Date datetime;
    private Integer mgDl;
    private String notes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

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
