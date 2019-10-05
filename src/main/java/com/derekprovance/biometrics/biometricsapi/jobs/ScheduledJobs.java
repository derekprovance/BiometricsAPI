package com.derekprovance.biometrics.biometricsapi.jobs;

import com.derekprovance.biometrics.biometricsapi.services.fitbit.FitbitAccessTokenService;
import com.derekprovance.biometrics.biometricsapi.services.fitbit.FoodLogService;
import com.derekprovance.biometrics.biometricsapi.services.fitbit.WaterLogService;
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
    private FitbitAccessTokenService fitbitAccessTokenService;

    @Autowired
    public ScheduledJobs(FoodLogService foodLogService, WaterLogService waterLogService, FitbitAccessTokenService fitbitAccessTokenService) {
        this.foodLogService = foodLogService;
        this.waterLogService = waterLogService;
        this.fitbitAccessTokenService = fitbitAccessTokenService;
    }

    @Bean
    @ConditionalOnProperty(value = "fitbit.enabled", havingValue = "true")
    public FitBitSyncJob scheduledJob() {
        return new FitBitSyncJob(foodLogService, waterLogService, fitbitAccessTokenService);
    }
}