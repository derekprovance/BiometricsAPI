package com.derekprovance.biometrics.biometricsapi.sleep;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Sleep {
    private @Id
    @GeneratedValue(strategy= GenerationType.AUTO) Integer id;
    private Integer dailyStatisticsId;
    private String sleepStart;
    private String sleepEnd;
    private Integer deepSleep;
    private Integer lightSleep;
    private Integer remSleep;
    private Integer awakeSleep;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDailyStatisticsId() {
        return dailyStatisticsId;
    }

    public void setDailyStatisticsId(Integer dailyStatisticsId) {
        this.dailyStatisticsId = dailyStatisticsId;
    }

    public String getSleepStart() {
        return sleepStart;
    }

    public void setSleepStart(String sleepStart) {
        this.sleepStart = sleepStart;
    }

    public String getSleepEnd() {
        return sleepEnd;
    }

    public void setSleepEnd(String sleepEnd) {
        this.sleepEnd = sleepEnd;
    }

    public Integer getDeepSleep() {
        return deepSleep;
    }

    public void setDeepSleep(Integer deepSleep) {
        this.deepSleep = deepSleep;
    }

    public Integer getLightSleep() {
        return lightSleep;
    }

    public void setLightSleep(Integer lightSleep) {
        this.lightSleep = lightSleep;
    }

    public Integer getRemSleep() {
        return remSleep;
    }

    public void setRemSleep(Integer remSleep) {
        this.remSleep = remSleep;
    }

    public Integer getAwakeSleep() {
        return awakeSleep;
    }

    public void setAwakeSleep(Integer awakeSleep) {
        this.awakeSleep = awakeSleep;
    }
}
