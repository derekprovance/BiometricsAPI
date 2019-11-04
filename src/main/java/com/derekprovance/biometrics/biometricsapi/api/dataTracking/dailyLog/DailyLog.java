package com.derekprovance.biometrics.biometricsapi.api.dataTracking.dailyLog;

import com.derekprovance.biometrics.biometricsapi.api.singleEntity.BaseSingleEntity;

import javax.persistence.Entity;

@Entity
public class DailyLog extends BaseSingleEntity {
    private String logEntry;
    private String tags;
    private DayRating dayRating;
    private DayRating productivityRating;

    public String getLogEntry() {
        return logEntry;
    }

    public void setLogEntry(String logEntry) {
        this.logEntry = logEntry;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public DayRating getDayRating() {
        return dayRating;
    }

    public void setDayRating(DayRating dayRating) {
        this.dayRating = dayRating;
    }

    public DayRating getProductivityRating() {
        return productivityRating;
    }

    public void setProductivityRating(DayRating productivityRating) {
        this.productivityRating = productivityRating;
    }
}
