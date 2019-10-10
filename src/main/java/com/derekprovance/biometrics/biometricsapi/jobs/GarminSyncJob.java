package com.derekprovance.biometrics.biometricsapi.jobs;

import com.derekprovance.biometrics.biometricsapi.services.garmin.GarminApiService;
import com.derekprovance.biometrics.biometricsapi.services.garmin.GarminConnectAuthService;
import com.derekprovance.biometrics.biometricsapi.services.garmin.GarminSyncService;
import org.springframework.scheduling.annotation.Scheduled;

public class GarminSyncJob {
    private GarminConnectAuthService garminConnectAuthService; //TODO(DEREK) - Implement
    private GarminSyncService garminSyncService;

    public GarminSyncJob(GarminConnectAuthService garminConnectAuthService, GarminSyncService garminSyncService) {
        this.garminConnectAuthService = garminConnectAuthService;
        this.garminSyncService = garminSyncService;
    }

    @Scheduled(fixedDelay = 500000)
    public void runGarminSyncJobs() {
        garminSyncService.sync();
    }
}
