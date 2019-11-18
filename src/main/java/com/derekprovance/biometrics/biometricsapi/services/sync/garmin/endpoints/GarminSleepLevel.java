package com.derekprovance.biometrics.biometricsapi.services.sync.garmin.endpoints;

import com.derekprovance.biometrics.biometricsapi.database.entity.SleepLevel;
import com.derekprovance.biometrics.biometricsapi.database.repository.SleepLevelRepository;
import com.derekprovance.biometrics.biometricsapi.services.sync.garmin.DTO.dailySleepData.SleepLevelDTO;
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
public class GarminSleepLevel {
    private final SleepLevelRepository sleepLevelRepository;

    @Autowired
    public GarminSleepLevel(SleepLevelRepository sleepLevelRepository) {
        this.sleepLevelRepository = sleepLevelRepository;
    }

    List<SleepLevel> syncSleepLevel(SleepLevelDTO[] sleepLevelDTOS, LocalDate date) {
        List<SleepLevel> sleepMovementData = new ArrayList<>();

        if(sleepLevelDTOS == null) {
            return sleepMovementData;
        }

        List<LocalDateTime> existingDates = getListOfDateTimeEntries(date);

        for(SleepLevelDTO sleepLevelValue : sleepLevelDTOS) {
            LocalDateTime localDateTime = sleepLevelValue.getStartGMT().toLocalDateTime();

            if(!existingDates.contains(localDateTime)) {
                SleepLevel newEntry = new SleepLevel();
                newEntry.setActivityLevel(sleepLevelValue.getActivityLevel());
                newEntry.setStart(sleepLevelValue.getStartGMT().toLocalDateTime());
                newEntry.setEnd(sleepLevelValue.getEndGMT().toLocalDateTime());
                sleepMovementData.add(newEntry);
            }
        }

        try {
            sleepLevelRepository.saveAll(sleepMovementData);
        } catch (DataAccessException ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }

        return sleepMovementData;
    }

    private List<LocalDateTime> getListOfDateTimeEntries(LocalDate date) {
        List<LocalDateTime> dates = new ArrayList<>();
        List<SleepLevel> allByDatetimeBetween = sleepLevelRepository.findAllByStartBetween(date.minus(1, ChronoUnit.DAYS).atStartOfDay(), date.atTime(LocalTime.MAX));

        for(SleepLevel sleepMovement : allByDatetimeBetween) {
            dates.add(sleepMovement.getStart());
        }

        return dates;
    }
}
