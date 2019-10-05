package com.derekprovance.biometrics.biometricsapi.api;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public abstract class AbstractApiController {

    private Calendar cal = Calendar.getInstance();

    public AbstractApiController() {
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    protected Date getBeginningOfDay(Date date) {
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();

    }

    protected Date getEndOfDay(Date date) {
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }
}
