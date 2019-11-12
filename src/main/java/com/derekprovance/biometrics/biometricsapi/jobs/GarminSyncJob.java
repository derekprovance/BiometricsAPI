package com.derekprovance.biometrics.biometricsapi.jobs;

import com.derekprovance.biometrics.biometricsapi.services.sync.garmin.GarminConnectAuthService;
import com.derekprovance.biometrics.biometricsapi.services.sync.garmin.GarminSyncService;
import org.springframework.scheduling.annotation.Scheduled;

import javax.security.auth.login.CredentialNotFoundException;

public class GarminSyncJob extends AbstractSyncJob {
    private final GarminSyncService garminSyncService;

    public GarminSyncJob(GarminConnectAuthService garminConnectAuthService, GarminSyncService garminSyncService) {
        this.garminSyncService = garminSyncService;
    }

    @Scheduled(cron = "0 0 0/3 * * ?")
    public void runGarminSyncJobs() throws CredentialNotFoundException {
        garminSyncService.sync(getYesterdayDate().toLocalDate());
    }
}
