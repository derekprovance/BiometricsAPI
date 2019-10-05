package com.derekprovance.biometrics.biometricsapi.jobs;

import com.derekprovance.biometrics.biometricsapi.services.garmin.GarminConnectAuthService;
import org.springframework.scheduling.annotation.Scheduled;

public class GarminSyncJob {
    private GarminConnectAuthService garminConnectAuthService;

    public GarminSyncJob(GarminConnectAuthService garminConnectAuthService) {
        this.garminConnectAuthService = garminConnectAuthService;
    }

    @Scheduled(fixedDelay = 500000)
    public void runGarminSyncJobs() {
        garminConnectAuthService.performTokenRefresh();
    }
}
