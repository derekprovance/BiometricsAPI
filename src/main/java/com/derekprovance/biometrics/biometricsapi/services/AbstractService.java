package com.derekprovance.biometrics.biometricsapi.services;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class AbstractService {
    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    protected static LocalDateTime convertTimestamp(Long timestamp) {
        Timestamp stamp = new Timestamp(timestamp);
        return stamp.toLocalDateTime();
    }

    protected static String convertDateToString(LocalDate date) {
        return format.format(date);
    }
}
