package com.derekprovance.biometrics.biometricsapi.services.sync.garmin.endpoints;

import com.derekprovance.biometrics.biometricsapi.database.entity.SleepMovement;
import com.derekprovance.biometrics.biometricsapi.database.repository.SleepMovementRepository;
import com.derekprovance.biometrics.biometricsapi.services.sync.garmin.DTO.dailySleepData.SleepMovementDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class GarminSleepMovement {
    private final SleepMovementRepository sleepMovementRepository;

    @Autowired
    public GarminSleepMovement(SleepMovementRepository sleepMovementRepository) {
        this.sleepMovementRepository = sleepMovementRepository;
    }

    List<SleepMovement> syncSleepMovement(SleepMovementDTO[] sleepMovement, LocalDate date) {
        List<SleepMovement> sleepMovementData = new ArrayList<>();

        if(sleepMovement == null) {
            return sleepMovementData;
        }

        List<LocalDateTime> existingDates = getListOfDateTimeEntries(date);

        for(SleepMovementDTO sleepMovementValue : sleepMovement) {
            LocalDateTime localDateTime = sleepMovementValue.getStartGMT().toLocalDateTime();

            if(!existingDates.contains(localDateTime)) {
                SleepMovement newEntry = new SleepMovement();
                newEntry.setActivityLevel(sleepMovementValue.getActivityLevel());
                newEntry.setStart(sleepMovementValue.getStartGMT().toLocalDateTime());
                newEntry.setEnd(sleepMovementValue.getEndGMT().toLocalDateTime());
                sleepMovementData.add(newEntry);
            }
        }

        try {
            sleepMovementRepository.saveAll(sleepMovementData);
        } catch (DataAccessException ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }

        return sleepMovementData;
    }

    private List<LocalDateTime> getListOfDateTimeEntries(LocalDate date) {
        List<LocalDateTime> dates = new ArrayList<>();
        List<SleepMovement> allByDatetimeBetween = sleepMovementRepository.findAllByStartBetween(date.minus(1, ChronoUnit.DAYS).atStartOfDay(), date.atTime(LocalTime.MAX));

        for(SleepMovement sleepMovement : allByDatetimeBetween) {
            dates.add(sleepMovement.getStart());
        }

        return dates;
    }
}
