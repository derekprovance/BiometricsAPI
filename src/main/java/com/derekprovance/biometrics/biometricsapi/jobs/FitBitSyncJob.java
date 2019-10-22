package com.derekprovance.biometrics.biometricsapi.jobs;

import com.derekprovance.biometrics.biometricsapi.services.fitbit.FitBitAccessTokenService;
import com.derekprovance.biometrics.biometricsapi.services.fitbit.FoodLogService;
import com.derekprovance.biometrics.biometricsapi.services.fitbit.WaterLogService;
import org.springframework.scheduling.annotation.Scheduled;

import javax.security.auth.login.CredentialException;

public class FitBitSyncJob extends AbstractSyncJob {

    private final FoodLogService foodLogService;
    private final WaterLogService waterLogService;
    private final FitBitAccessTokenService fitbitAccessTokenService;

    public FitBitSyncJob(FoodLogService foodLogService, WaterLogService waterLogService, FitBitAccessTokenService fitbitAccessTokenService) {
        this.foodLogService = foodLogService;
        this.waterLogService = waterLogService;
        this.fitbitAccessTokenService = fitbitAccessTokenService;
    }

    @Scheduled(cron = "0 0 * * * ?")
    public void runFitBitJobs() throws CredentialException {
        foodLogService.syncWithDatabase();
        waterLogService.syncWithDatabase();
    }

    @Scheduled(cron = "0 0 1 * * ?")
    public void runFitBitJobsForYesterday() throws CredentialException {
        foodLogService.syncWithDatabase(getYesterdayDate().toLocalDate());
        waterLogService.syncWithDatabase(getYesterdayDate().toLocalDate());
    }

    @Scheduled(fixedRate = 18000000)
    public void runTokenRefresh() throws CredentialException {
        fitbitAccessTokenService.refreshAccessToken();
    }
}
