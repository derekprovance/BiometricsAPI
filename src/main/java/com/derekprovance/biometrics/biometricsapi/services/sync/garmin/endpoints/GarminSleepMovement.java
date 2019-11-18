package com.derekprovance.biometrics.biometricsapi.services.sync.garmin.endpoints;

import com.derekprovance.biometrics.biometricsapi.database.entity.SleepMovement;
import com.derekprovance.biometrics.biometricsapi.database.repository.SleepMovementRepository;
import com.derekprovance.biometrics.biometricsapi.services.sync.garmin.DTO.dailySleepData.SleepMovementDTO;
import com.derekprovance.biometrics.biometricsapi.services.sync.garmin.GarminApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GarminSleepMovement {
    private final SleepMovementRepository sleepMovementRepository;
    private final GarminApiService garminApiService;

    @Autowired
    public GarminSleepMovement(SleepMovementRepository sleepMovementRepository, GarminApiService garminApiService) {
        this.sleepMovementRepository = sleepMovementRepository;
        this.garminApiService = garminApiService;
    }

    List<SleepMovement> syncSleepMovement(SleepMovementDTO[] sleepMovement) {
        List<SleepMovement> sleepMovementData = new ArrayList<>();

        if(sleepMovement == null) {
            return sleepMovementData;
        }

        for(SleepMovementDTO sleepMovementValue : sleepMovement) {
            SleepMovement newEntry = new SleepMovement();
            newEntry.setActivityLevel(sleepMovementValue.getActivityLevel());
            newEntry.setStart(sleepMovementValue.getStartGMT().toLocalDateTime());
            newEntry.setEnd(sleepMovementValue.getEndGMT().toLocalDateTime());
            sleepMovementData.add(newEntry);
        }

        try {
            sleepMovementRepository.saveAll(sleepMovementData);
        } catch (DataAccessException ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }

        return sleepMovementData;
    }
}
