package com.derekprovance.biometrics.biometricsapi.services.garmin;

import com.derekprovance.biometrics.biometricsapi.api.dailyStatistics.DailyStatistics;
import com.derekprovance.biometrics.biometricsapi.api.dailyStatistics.DailyStatisticsRepository;
import com.derekprovance.biometrics.biometricsapi.api.garmin.ItemSyncCount;
import com.derekprovance.biometrics.biometricsapi.api.hrData.HrData;
import com.derekprovance.biometrics.biometricsapi.api.hrData.HrDataRepository;
import com.derekprovance.biometrics.biometricsapi.api.movementData.MovementData;
import com.derekprovance.biometrics.biometricsapi.api.movementData.MovementDataRepository;
import com.derekprovance.biometrics.biometricsapi.api.sleep.Sleep;
import com.derekprovance.biometrics.biometricsapi.api.sleep.SleepRepository;
import com.derekprovance.biometrics.biometricsapi.api.sleepMovement.SleepMovement;
import com.derekprovance.biometrics.biometricsapi.api.sleepMovement.SleepMovementRepository;
import com.derekprovance.biometrics.biometricsapi.services.AbstractService;
import com.derekprovance.biometrics.biometricsapi.services.garmin.DTO.DailyHeartRate;
import com.derekprovance.biometrics.biometricsapi.services.garmin.DTO.DailyMovementData;
import com.derekprovance.biometrics.biometricsapi.services.garmin.DTO.DailyUserSummary;
import com.derekprovance.biometrics.biometricsapi.services.garmin.DTO.dailySleepData.DailySleepDTO;
import com.derekprovance.biometrics.biometricsapi.services.garmin.DTO.dailySleepData.DailySleepData;
import com.derekprovance.biometrics.biometricsapi.services.garmin.DTO.dailySleepData.SleepMovementDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class GarminSyncService extends AbstractService {
    private GarminApiService garminApiService;
    private DailyStatisticsRepository dailyStatisticsRepository;
    private HrDataRepository hrDataRepository;
    private MovementDataRepository movementDataRepository;
    private SleepMovementRepository sleepMovementRepository;
    private SleepRepository sleepRepository;

    private static final Logger log = LoggerFactory.getLogger(GarminSyncService.class);

    @Autowired
    public GarminSyncService(
            GarminApiService garminApiService,
            DailyStatisticsRepository dailyStatisticsRepository,
            HrDataRepository hrDataRepository,
            MovementDataRepository movementDataRepository,
            SleepMovementRepository sleepMovementRepository,
            SleepRepository sleepRepository
    ) {
        this.garminApiService = garminApiService;
        this.dailyStatisticsRepository = dailyStatisticsRepository;
        this.hrDataRepository = hrDataRepository;
        this.movementDataRepository = movementDataRepository;
        this.sleepMovementRepository = sleepMovementRepository;
        this.sleepRepository = sleepRepository;
    }

    public ItemSyncCount sync(LocalDate date) {
        ItemSyncCount itemSyncCount = new ItemSyncCount();
        itemSyncCount.setDate(date);

        try {
            if(!dataAlreadySynced(date)) {
                log.info("Syncing data from Garmin for " + date);
                syncDailyStatistics(date);

                itemSyncCount.setHrData(syncHrData(date).size());
                itemSyncCount.setMovementData(syncMovementData(date).size());
                itemSyncCount.setSleepMovementData(syncSleepData(date));
            } else {
                itemSyncCount.setHrData(0);
                itemSyncCount.setMovementData(0);
                itemSyncCount.setSleepMovementData(0);
            }
        } catch (CredentialNotFoundException e) {
            e.printStackTrace();
        }

        return itemSyncCount;
    }

    private Boolean dataAlreadySynced(LocalDate date) {
        final DailyStatistics dailyStatistics = dailyStatisticsRepository.findByEntryDate(date);

        if(dailyStatistics == null) {
            return false;
        }

        //TODO(DEREK) - make timestamps unique constraints in database and deny entry that way
        return !(dailyStatistics.getHighlyActiveSeconds() == null &&
                dailyStatistics.getHighStressDuration() == null &&
                dailyStatistics.getLowStressDuration() == null &&
                dailyStatistics.getMaxHr() == null &&
                dailyStatistics.getMinHr() == null &&
                dailyStatistics.getRestingHr() == null &&
                dailyStatistics.getSedentarySeconds() == null &&
                dailyStatistics.getSleepingSeconds() == null &&
                dailyStatistics.getTotalSteps() == null);
    }

    private void syncDailyStatistics(LocalDate date) throws CredentialNotFoundException {
        DailyStatistics dailyStatisticsEntry = dailyStatisticsRepository.findByEntryDate(date);
        if(dailyStatisticsEntry == null) {
            dailyStatisticsEntry = new DailyStatistics();
        }

        final DailyUserSummary userSummary = garminApiService.getUserSummary(date);

        dailyStatisticsEntry.setEntryDate(userSummary.getCalendarDate());
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

    private int syncSleepData(LocalDate date) throws CredentialNotFoundException {
        final DailySleepData dailySleepData = garminApiService.getDailySleepData(date);
        Sleep sleep = null;

        final List<SleepMovement> movementsInSleep = syncSleepMovement(dailySleepData.getSleepMovement());
        if(movementsInSleep != null && movementsInSleep.size() > 0) {
            sleep = syncSleepMeta(dailySleepData.getDailySleepDTO());
        }

        int savedItems = 0;
        if(sleep != null) {
            savedItems = movementsInSleep.size() + 1;
        }

        return savedItems;
    }

    private Sleep syncSleepMeta(DailySleepDTO dailySleepDTO) {
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
            return null;
        }

        return sleep;
    }

    private List<SleepMovement> syncSleepMovement(SleepMovementDTO[] sleepMovement) {
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
            return null;
        }

        return sleepMovementData;
    }

    private List<MovementData> syncMovementData(LocalDate date) throws CredentialNotFoundException {
        final DailyMovementData dailyMovement = garminApiService.getDailyMovement(date);
        Object[][] dailyMovementValues = dailyMovement.getMovementValues();
        List<MovementData> movementData = new ArrayList<>();

        if(dailyMovementValues != null) {
            for(Object[] dailyMovementValue : dailyMovementValues) {
                MovementData movementDataEntry = new MovementData();
                movementDataEntry.setEventTime(convertTimestamp((Long) dailyMovementValue[0]));
                movementDataEntry.setMovement((Double) dailyMovementValue[1]);
                movementData.add(movementDataEntry);
            }

            movementDataRepository.saveAll(movementData);
        }

        return movementData;
    }

    private List<HrData> syncHrData(LocalDate date) throws CredentialNotFoundException {
        final DailyHeartRate dailyHrData = garminApiService.getDailyHrData(date);
        List<HrData> hrData = new ArrayList<>();
        Object[][] heartRateValues = dailyHrData.getHeartRateValues();

        if(heartRateValues != null) {
            for (Object[] heartRateValue : heartRateValues) {
                HrData newEntry = new HrData();
                newEntry.setEventTime(convertTimestamp((Long) heartRateValue[0]));
                newEntry.setHrValue((Integer) heartRateValue[1]);
                hrData.add(newEntry);
            }

            hrDataRepository.saveAll(hrData);
        }

        return hrData;
    }


}
