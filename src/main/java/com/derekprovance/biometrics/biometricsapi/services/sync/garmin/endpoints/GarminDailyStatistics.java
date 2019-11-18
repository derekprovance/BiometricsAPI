package com.derekprovance.biometrics.biometricsapi.services.sync.garmin.endpoints;

import com.derekprovance.biometrics.biometricsapi.database.entity.DailyStatistics;
import com.derekprovance.biometrics.biometricsapi.database.repository.DailyStatisticsRepository;
import com.derekprovance.biometrics.biometricsapi.services.sync.garmin.DTO.DailyUserSummary;
import com.derekprovance.biometrics.biometricsapi.services.sync.garmin.GarminApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialNotFoundException;
import java.time.LocalDate;

@Service
public class GarminDailyStatistics {
    private final DailyStatisticsRepository dailyStatisticsRepository;
    private final GarminApiService garminApiService;

    @Autowired
    public GarminDailyStatistics(DailyStatisticsRepository dailyStatisticsRepository, GarminApiService garminApiService) {
        this.dailyStatisticsRepository = dailyStatisticsRepository;
        this.garminApiService = garminApiService;
    }

    public void syncDailyStatistics(LocalDate date) throws CredentialNotFoundException {
        DailyStatistics dailyStatisticsEntry = (DailyStatistics) dailyStatisticsRepository.findFirstByDate(date);
        if(dailyStatisticsEntry == null) {
            dailyStatisticsEntry = new DailyStatistics();
        }

        final DailyUserSummary userSummary = garminApiService.getUserSummary(date);

        dailyStatisticsEntry.setDate(userSummary.getCalendarDate());
        dailyStatisticsEntry.setHighlyActiveSeconds(userSummary.getHighlyActiveSeconds());
        dailyStatisticsEntry.setSedentarySeconds(userSummary.getSedentarySeconds());
        dailyStatisticsEntry.setSleepingSeconds(userSummary.getSleepingSeconds());
        dailyStatisticsEntry.setMaxHr(userSummary.getMaxHeartRate());
        dailyStatisticsEntry.setMinHr(userSummary.getMinHeartRate());
        dailyStatisticsEntry.setRestingHr(userSummary.getRestingHeartRate());
        dailyStatisticsEntry.setHighStressDuration(userSummary.getHighStressDuration());
        dailyStatisticsEntry.setMaxStressLevel(userSummary.getMaxStressLevel());
        dailyStatisticsEntry.setMediumStressDuration(userSummary.getMediumStressDuration());
        dailyStatisticsEntry.setLowStressDuration(userSummary.getLowStressDuration());
        dailyStatisticsEntry.setTotalSteps(userSummary.getTotalSteps());

        dailyStatisticsRepository.save(dailyStatisticsEntry);
    }
}
