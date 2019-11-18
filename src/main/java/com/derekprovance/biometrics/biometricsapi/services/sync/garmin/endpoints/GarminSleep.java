package com.derekprovance.biometrics.biometricsapi.services.sync.garmin.endpoints;

import com.derekprovance.biometrics.biometricsapi.database.entity.Sleep;
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
import java.util.List;

@Service
public class GarminSleep {
    private final SleepRepository sleepRepository;
    private final GarminSleepMovement sleepMovement;
    private final GarminApiService garminApiService;

    @Autowired
    public GarminSleep(SleepRepository sleepRepository, GarminSleepMovement sleepMovement, GarminApiService garminApiService) {
        this.sleepRepository = sleepRepository;
        this.sleepMovement = sleepMovement;
        this.garminApiService = garminApiService;
    }

    public int syncSleepData(LocalDate date) throws CredentialNotFoundException {
        final DailySleepData dailySleepData = garminApiService.getDailySleepData(date);

        syncSleepMeta(dailySleepData.getDailySleepDTO());
        final List<SleepMovement> movementsInSleep = sleepMovement.syncSleepMovement(dailySleepData.getSleepMovement());

        return movementsInSleep.size();
    }

    private void syncSleepMeta(DailySleepDTO dailySleepDTO) {
        Sleep sleep = new Sleep();

        sleep.setAwakeSleep(dailySleepDTO.getAwakeSleepSeconds());
        sleep.setDeepSleep(dailySleepDTO.getDeepSleepSeconds());
        sleep.setLightSleep(dailySleepDTO.getLightSleepSeconds());
        sleep.setRemSleep(dailySleepDTO.getRemSleepSeconds());
        sleep.setSleepStart(dailySleepDTO.getSleepStartTimestampGMT().toLocalDateTime());
        sleep.setSleepEnd(dailySleepDTO.getSleepEndTimestampGMT().toLocalDateTime());

        try {
            sleepRepository.save(sleep);
        } catch (DataAccessException ex) {
            ex.printStackTrace();
        }
    }
}
