package com.derekprovance.biometrics.biometricsapi.api.dataTracking.dailyLog;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class DailyLog {
    private @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) Integer id;

    private LocalDate date;
    private String logEntry;
    private String tags;
    private DayRating dayRating;
    private DayRating productivityRating;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

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
