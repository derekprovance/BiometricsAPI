package com.derekprovance.biometrics.biometricsapi.jobs;

import com.derekprovance.biometrics.biometricsapi.services.fitbit.FitBitAccessTokenService;
import com.derekprovance.biometrics.biometricsapi.services.fitbit.FoodLogService;
import com.derekprovance.biometrics.biometricsapi.services.fitbit.WaterLogService;
import com.derekprovance.biometrics.biometricsapi.services.garmin.GarminApiService;
import com.derekprovance.biometrics.biometricsapi.services.garmin.GarminConnectAuthService;
import com.derekprovance.biometrics.biometricsapi.services.garmin.GarminSyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class ScheduledJobs {

    private FoodLogService foodLogService;
    private WaterLogService waterLogService;
    private FitBitAccessTokenService fitbitAccessTokenService;
    private GarminConnectAuthService garminConnectAuthService;
    private GarminSyncService garminSyncService;

    @Autowired
    public ScheduledJobs(
            FoodLogService foodLogService,
            WaterLogService waterLogService,
            FitBitAccessTokenService fitbitAccessTokenService,
            GarminConnectAuthService garminConnectAuthService,
            GarminSyncService garminSyncService
    ) {
        this.foodLogService = foodLogService;
        this.waterLogService = waterLogService;
        this.fitbitAccessTokenService = fitbitAccessTokenService;
        this.garminConnectAuthService = garminConnectAuthService;
        this.garminSyncService = garminSyncService;
    }

    @Bean
    @ConditionalOnProperty(value = "fitbit.enabled", havingValue = "true")
    public FitBitSyncJob fitBitSyncJob() {
        return new FitBitSyncJob(foodLogService, waterLogService, fitbitAccessTokenService);
    }

    @Bean
    @ConditionalOnProperty(value = "garmin.enabled", havingValue = "true")
    public GarminSyncJob garminSyncJob() {
        return new GarminSyncJob(garminConnectAuthService, garminSyncService);
    }
}