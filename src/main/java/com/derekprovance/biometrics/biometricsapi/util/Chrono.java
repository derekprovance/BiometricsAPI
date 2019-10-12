package com.derekprovance.biometrics.biometricsapi.util;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.util.Date;

public class Chrono {
    public static Date convertTimestamp(Long timestamp) {
        Timestamp stamp = new Timestamp(timestamp);
        return new Date(stamp.getTime());
    }

    public static String convertDateToString(Date date) {
        return date.toInstant()
                .atZone(ZoneId.of("UTC"))
                .toLocalDate().toString();
    }
}
