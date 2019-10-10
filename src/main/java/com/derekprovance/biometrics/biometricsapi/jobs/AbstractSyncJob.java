package com.derekprovance.biometrics.biometricsapi.jobs;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public abstract class AbstractSyncJob {
    protected Date getYesterdayDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        cal.add(Calendar.DATE, -1);

        return cal.getTime();
    }
}
