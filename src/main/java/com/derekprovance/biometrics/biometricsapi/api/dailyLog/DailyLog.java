package com.derekprovance.biometrics.biometricsapi.api.dailyLog;

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
    private DayRating dayRating;

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

    public DayRating getDayRating() {
        return dayRating;
    }

    public void setDayRating(DayRating dayRating) {
        this.dayRating = dayRating;
    }
}
