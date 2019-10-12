package com.derekprovance.biometrics.biometricsapi.services;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.util.Date;

public abstract class AbstractService {
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
