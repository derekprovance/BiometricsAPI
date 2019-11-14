package com.derekprovance.biometrics.biometricsapi.database.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Sleep {
    private @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) Integer id;

    private LocalDateTime sleepStart;
    private LocalDateTime sleepEnd;
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

    public LocalDateTime getSleepStart() {
        return sleepStart;
    }

    public void setSleepStart(LocalDateTime sleepStart) {
        this.sleepStart = sleepStart;
    }

    public LocalDateTime getSleepEnd() {
        return sleepEnd;
    }

    public void setSleepEnd(LocalDateTime sleepEnd) {
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
