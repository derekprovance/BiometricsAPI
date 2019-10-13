package com.derekprovance.biometrics.biometricsapi.jobs;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public abstract class AbstractSyncJob {
    LocalDateTime getYesterdayDate() {
        return LocalDateTime.now().minus(1, ChronoUnit.DAYS);
    }
}
