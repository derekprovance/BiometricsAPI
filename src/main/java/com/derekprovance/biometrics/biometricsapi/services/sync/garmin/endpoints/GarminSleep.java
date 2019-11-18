package com.derekprovance.biometrics.biometricsapi.services.sync.garmin.endpoints;

import com.derekprovance.biometrics.biometricsapi.database.entity.Sleep;
import com.derekprovance.biometrics.biometricsapi.database.entity.SleepLevel;
import com.derekprovance.biometrics.biometricsapi.database.entity.SleepMovement;
import com.derekprovance.biometrics.biometricsapi.database.repository.SleepRepository;
import com.derekprovance.biometrics.biometricsapi.services.sync.garmin.DTO.dailySleepData.DailySleepDTO;
import com.derekprovance.biometrics.biometricsapi.services.sync.garmin.DTO.dailySleepData.DailySleepData;
import com.derekprovance.biometrics.biometricsapi.services.sync.garmin.GarminApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class GarminSleep {
    private final SleepRepository sleepRepository;
    private final GarminSleepMovement sleepMovement;
    private final GarminSleepLevel sleepLevel;
    private final GarminApiService garminApiService;

    @Autowired
    public GarminSleep(SleepRepository sleepRepository, GarminSleepMovement sleepMovement, GarminSleepLevel sleepLevel, GarminApiService garminApiService) {
        this.sleepRepository = sleepRepository;
        this.sleepMovement = sleepMovement;
        this.sleepLevel = sleepLevel;
        this.garminApiService = garminApiService;
    }

    public int syncSleepData(LocalDate date) throws CredentialNotFoundException {
        final DailySleepData dailySleepData = garminApiService.getDailySleepData(date);

        syncSleepMeta(dailySleepData.getDailySleepDTO(), date);
        final List<SleepMovement> movementsInSleep = sleepMovement.syncSleepMovement(dailySleepData.getSleepMovement(), date);
        final List<SleepLevel> sleepLevels = sleepLevel.syncSleepLevel(dailySleepData.getSleepLevels(), date);

        return movementsInSleep.size() + sleepLevels.size();
    }

    private void syncSleepMeta(DailySleepDTO dailySleepDTO, LocalDate date) {
        Sleep sleep = sleepRepository.findFirstBySleepStartBetween(date.minus(1, ChronoUnit.DAYS).atStartOfDay(), date.atTime(LocalTime.MAX));

        if(sleep == null) {
            sleep = new Sleep();
        }

        sleep.setAwakeSleep(dailySleepDTO.getAwakeSleepSeconds());
        sleep.setDeepSleep(dailySleepDTO.getDeepSleepSeconds());
        sleep.setLightSleep(dailySleepDTO.getLightSleepSeconds());
        sleep.setRemSleep(dailySleepDTO.getRemSleepSeconds());
        sleep.setSleepStart(dailySleepDTO.getSleepStartTimestampGMT().toLocalDateTime());
        sleep.setSleepEnd(dailySleepDTO.getSleepEndTimestampGMT().toLocalDateTime());

        sleepRepository.save(sleep);
    }
}
