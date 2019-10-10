package com.derekprovance.biometrics.biometricsapi.jobs;

import com.derekprovance.biometrics.biometricsapi.services.garmin.GarminConnectAuthService;
import com.derekprovance.biometrics.biometricsapi.services.garmin.GarminSyncService;
import org.springframework.scheduling.annotation.Scheduled;

public class GarminSyncJob extends AbstractSyncJob {
    private GarminConnectAuthService garminConnectAuthService; //TODO(DEREK) - Implement
    private GarminSyncService garminSyncService;

    public GarminSyncJob(GarminConnectAuthService garminConnectAuthService, GarminSyncService garminSyncService) {
        this.garminConnectAuthService = garminConnectAuthService;
        this.garminSyncService = garminSyncService;
    }

    @Scheduled(cron = "0 0 1 * * ?")
    public void runGarminSyncJobs() {
        garminSyncService.sync(getYesterdayDate());
    }
}
