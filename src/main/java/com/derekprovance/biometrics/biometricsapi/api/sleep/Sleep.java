package com.derekprovance.biometrics.biometricsapi.api.sleep;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Sleep {
    private @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) Integer id;

    private Date sleepStart;
    private Date sleepEnd;
    private Long deepSleep;
    private Long lightSleep;
    private Long remSleep;
    private Long awakeSleep;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getSleepStart() {
        return sleepStart;
    }

    public void setSleepStart(Date sleepStart) {
        this.sleepStart = sleepStart;
    }

    public Date getSleepEnd() {
        return sleepEnd;
    }

    public void setSleepEnd(Date sleepEnd) {
        this.sleepEnd = sleepEnd;
    }

    public Long getDeepSleep() {
        return deepSleep;
    }

    public void setDeepSleep(Long deepSleep) {
        this.deepSleep = deepSleep;
    }

    public Long getLightSleep() {
        return lightSleep;
    }

    public void setLightSleep(Long lightSleep) {
        this.lightSleep = lightSleep;
    }

    public Long getRemSleep() {
        return remSleep;
    }

    public void setRemSleep(Long remSleep) {
        this.remSleep = remSleep;
    }

    public Long getAwakeSleep() {
        return awakeSleep;
    }

    public void setAwakeSleep(Long awakeSleep) {
        this.awakeSleep = awakeSleep;
    }
}
