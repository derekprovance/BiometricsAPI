package com.derekprovance.biometrics.biometricsapi.services;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;

public abstract class AbstractService {
    protected final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    protected static Date convertTimestamp(Long timestamp) {
        Timestamp stamp = new Timestamp(timestamp);
        return new Date(stamp.getTime());
    }

    protected static String convertDateToString(Date date) {
        return date.toInstant()
                .atZone(ZoneId.of("UTC"))
                .toLocalDate().toString();
    }
}
