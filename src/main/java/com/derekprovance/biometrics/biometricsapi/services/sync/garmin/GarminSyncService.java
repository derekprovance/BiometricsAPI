package com.derekprovance.biometrics.biometricsapi.services.sync.garmin;

import com.derekprovance.biometrics.biometricsapi.api.utility.garmin.ItemSyncCount;
import com.derekprovance.biometrics.biometricsapi.services.AbstractService;
import com.derekprovance.biometrics.biometricsapi.services.sync.garmin.endpoints.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialNotFoundException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class GarminSyncService extends AbstractService {
    private static final Logger log = LoggerFactory.getLogger(GarminSyncService.class);
    private final GarminHrData garminHrData;
    private final GarminMovement garminMovement;
    private final GarminSleep garminSleep;
    private final GarminDailyStatistics garminDailyStatistics;

    @Autowired
    public GarminSyncService(
            GarminHrData garminHrData,
            GarminMovement garminMovement,
            GarminSleep garminSleep,
            GarminDailyStatistics garminDailyStatistics
    ) {
        this.garminHrData = garminHrData;
        this.garminMovement = garminMovement;
        this.garminSleep = garminSleep;
        this.garminDailyStatistics = garminDailyStatistics;
    }

    public ItemSyncCount sync(LocalDate start, LocalDate end) throws CredentialNotFoundException {
        ItemSyncCount result = new ItemSyncCount();

        LocalDate current = start;
        while(end.compareTo(current) >= 0) {
            ItemSyncCount tempSyncCount = sync(current);
            result.addSyncedItems(tempSyncCount);

            current = current.plus(1, ChronoUnit.DAYS);
        }

        return result;
    }

    public ItemSyncCount sync(LocalDate date) throws CredentialNotFoundException {
        ItemSyncCount itemSyncCount = new ItemSyncCount();
        itemSyncCount.setDate(date);

        log.info("Syncing data from Garmin for " + date);
        garminDailyStatistics.syncDailyStatistics(date);

        itemSyncCount.setHrData(garminHrData.syncHrData(date).size());
        itemSyncCount.setMovementData(garminMovement.syncMovementData(date).size());
        itemSyncCount.setSleepData(garminSleep.syncSleepData(date));

        return itemSyncCount;
    }
}
