package com.derekprovance.biometrics.biometricsapi.services.garmin;

import com.derekprovance.biometrics.biometricsapi.api.dailyStatistics.DailyStatistics;
import com.derekprovance.biometrics.biometricsapi.api.dailyStatistics.DailyStatisticsRepository;
import com.derekprovance.biometrics.biometricsapi.api.hrData.HrData;
import com.derekprovance.biometrics.biometricsapi.api.hrData.HrDataRepository;
import com.derekprovance.biometrics.biometricsapi.api.movementData.MovementData;
import com.derekprovance.biometrics.biometricsapi.api.movementData.MovementDataRepository;
import com.derekprovance.biometrics.biometricsapi.api.sleepMovement.SleepMovement;
import com.derekprovance.biometrics.biometricsapi.api.sleepMovement.SleepMovementRepository;
import com.derekprovance.biometrics.biometricsapi.services.garmin.DTO.DailyHeartRate;
import com.derekprovance.biometrics.biometricsapi.services.garmin.DTO.DailyMovementData;
import com.derekprovance.biometrics.biometricsapi.services.garmin.DTO.DailyUserSummary;
import com.derekprovance.biometrics.biometricsapi.services.garmin.DTO.dailySleepData.DailySleepData;
import com.derekprovance.biometrics.biometricsapi.services.garmin.DTO.dailySleepData.SleepMovementDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GarminSyncService {
    //TODO - consider protection for previously run entries

    private GarminApiService garminApiService;
    private DailyStatisticsRepository dailyStatisticsRepository;
    private HrDataRepository hrDataRepository;
    private MovementDataRepository movementDataRepository;
    private SleepMovementRepository sleepMovementRepository;

    @Autowired
    public GarminSyncService(
            GarminApiService garminApiService,
            DailyStatisticsRepository dailyStatisticsRepository,
            HrDataRepository hrDataRepository,
            MovementDataRepository movementDataRepository,
            SleepMovementRepository sleepMovementRepository
    ) {
        this.garminApiService = garminApiService;
        this.dailyStatisticsRepository = dailyStatisticsRepository;
        this.hrDataRepository = hrDataRepository;
        this.movementDataRepository = movementDataRepository;
        this.sleepMovementRepository = sleepMovementRepository;
    }

    public void sync() {
        sync(new Date());
    }

    public void sync(Date date) {
        syncHrData(date);
        syncMovementData(date);
        syncSleepMovementData(date);
        syncDailyStatistics(date);
    }

    private void syncDailyStatistics(Date date) {
        if(dailyStatisticsRepository.findByEntryDate(date) != null) {
            return;
        }

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

    private void syncSleepMovementData(Date date) {
        final DailySleepData dailySleepData = garminApiService.getDailySleepData(date);
        final SleepMovementDTO[] sleepMovement = dailySleepData.getSleepMovement();
        List<SleepMovement> sleepMovementData = new ArrayList<>();

        for(SleepMovementDTO sleepMovementValue : sleepMovement) {
            SleepMovement newEntry = new SleepMovement();
            newEntry.setActivityLevel(sleepMovementValue.getActivityLevel());
            newEntry.setStart(sleepMovementValue.getStartGMT());
            newEntry.setEnd(sleepMovementValue.getEndGMT());
            sleepMovementData.add(newEntry);
        }

        sleepMovementRepository.saveAll(sleepMovementData);
    }

    private void syncMovementData(Date date) {
        final DailyMovementData dailyMovement = garminApiService.getDailyMovement(date);
        Object[][] dailyMovementValues = dailyMovement.getMovementValues();
        List<MovementData> movementData = new ArrayList<>();

        for(Object[] dailyMovementValue : dailyMovementValues) {
            MovementData movementDataEntry = new MovementData();
            movementDataEntry.setEventTime(convertTimestamp((Long) dailyMovementValue[0]));
            movementDataEntry.setMovement((Double) dailyMovementValue[1]);
            movementData.add(movementDataEntry);
        }

        movementDataRepository.saveAll(movementData);
    }

    private void syncHrData(Date date) {
        final DailyHeartRate dailyHrData = garminApiService.getDailyHrData(date);
        List<HrData> hrData = new ArrayList<>();

        Object[][] heartRateValues = dailyHrData.getHeartRateValues();
        for (Object[] heartRateValue : heartRateValues) {
            HrData newEntry = new HrData();
            newEntry.setEventTime(convertTimestamp((Long) heartRateValue[0]));
            newEntry.setHrValue((Integer) heartRateValue[1]);
            hrData.add(newEntry);
        }

        hrDataRepository.saveAll(hrData);
    }

    private Date convertTimestamp(Long timestamp) {
        Timestamp stamp = new Timestamp(timestamp);
        return new Date(stamp.getTime());
    }
}
