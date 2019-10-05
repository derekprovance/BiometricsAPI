package com.derekprovance.biometrics.biometricsapi.jobs;

import com.derekprovance.biometrics.biometricsapi.services.fitbit.FitbitAccessTokenService;
import com.derekprovance.biometrics.biometricsapi.services.fitbit.FoodLogService;
import com.derekprovance.biometrics.biometricsapi.services.fitbit.WaterLogService;
import org.springframework.scheduling.annotation.Scheduled;

public class FitBitSyncJob {

    private FoodLogService foodLogService;
    private WaterLogService waterLogService;
    private FitbitAccessTokenService fitbitAccessTokenService;

    public FitBitSyncJob(FoodLogService foodLogService, WaterLogService waterLogService, FitbitAccessTokenService fitbitAccessTokenService) {
        this.foodLogService = foodLogService;
        this.waterLogService = waterLogService;
        this.fitbitAccessTokenService = fitbitAccessTokenService;
    }

    @Scheduled(fixedDelay = 3600000)
    public void runFitBitJobs() {
        foodLogService.syncWithDatabase();
        waterLogService.syncWithDatabase();
    }

    @Scheduled(fixedRate = 18000000)
    public void runTokenRefresh() {
        fitbitAccessTokenService.performTokenRefresh();
    }
}