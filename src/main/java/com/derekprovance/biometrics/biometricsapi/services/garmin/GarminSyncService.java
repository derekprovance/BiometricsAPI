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

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GarminSyncService {
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

    public ItemSyncCount sync(Date date) {
        ItemSyncCount itemSyncCount = new ItemSyncCount();
        itemSyncCount.setDate(date);

        if(!dailyStatisticsExists(date)) {
            log.info("Syncing data from Garmin for " + date);
            syncDailyStatistics(date);

            itemSyncCount.setDailyStatistic(true);
            itemSyncCount.setHrData(syncHrData(date).size());
            itemSyncCount.setMovementData(syncMovementData(date).size());
            itemSyncCount.setSleepMovementData(syncSleepData(date));
        } else {
            itemSyncCount.setDailyStatistic(false);
            itemSyncCount.setHrData(0);
            itemSyncCount.setMovementData(0);
            itemSyncCount.setSleepMovementData(0);
        }

        return itemSyncCount;
    }

    private Boolean dailyStatisticsExists(Date date) {
        return dailyStatisticsRepository.findByEntryDate(date) != null;
    }

    private void syncDailyStatistics(Date date) {
        final DailyUserSummary userSummary = garminApiService.getUserSummary(date);

        DailyStatistics dailyStatisticsEntry = new DailyStatistics();
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

    private int syncSleepData(Date date) {
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
        sleep.setSleepStart(dailySleepDTO.getSleepStartTimestampGMT());
        sleep.setSleepEnd(dailySleepDTO.getSleepEndTimestampGMT());

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
            newEntry.setStart(sleepMovementValue.getStartGMT());
            newEntry.setEnd(sleepMovementValue.getEndGMT());
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

    private List<MovementData> syncMovementData(Date date) {
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

    private List<HrData> syncHrData(Date date) {
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

    private Date convertTimestamp(Long timestamp) {
        Timestamp stamp = new Timestamp(timestamp);
        return new Date(stamp.getTime());
    }
}
