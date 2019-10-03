package com.derekprovance.biometrics.biometricsapi.services.fitbit;

import com.derekprovance.biometrics.biometricsapi.api.meals.MealEntry;
import com.derekprovance.biometrics.biometricsapi.api.meals.MealRepository;
import com.derekprovance.biometrics.biometricsapi.api.waterConsumptionData.WaterConsumption;
import com.derekprovance.biometrics.biometricsapi.api.waterConsumptionData.WaterConsumptionRepository;
import com.derekprovance.biometrics.biometricsapi.services.fitbit.DTO.meals.FitbitFoodEndpointDTO;
import com.derekprovance.biometrics.biometricsapi.services.fitbit.DTO.meals.MealEntryDTO;
import com.derekprovance.biometrics.biometricsapi.services.fitbit.DTO.water.WaterLogDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class WaterLogService {

    private static final Logger log = LoggerFactory.getLogger(WaterLogService.class);

    private WaterConsumptionRepository waterConsumptionRepository;
    private FitbitAPIService fitbitAPIService;

    @Autowired
    public WaterLogService(WaterConsumptionRepository waterConsumptionRepository, FitbitAPIService fitbitAPIService) {
        this.waterConsumptionRepository = waterConsumptionRepository;
        this.fitbitAPIService = fitbitAPIService;
    }

    @Scheduled(fixedRate = 3600000)
    public void syncWithDatabase() {
        syncWithDatabase(new Date());
    }

    public void syncWithDatabase(Date date) {
        final WaterLogDTO entriesForDate = fitbitAPIService.performWaterLog(date);

        if(entriesForDate != null) {
            processEntries(date, entriesForDate);
        }
    }

    private void processEntries(Date date, WaterLogDTO waterLogDTO) {
        WaterConsumption waterConsumption;

        waterConsumption = waterConsumptionRepository.findByDate(date);

        if(waterConsumption == null) {
            waterConsumption = new WaterConsumption();
            waterConsumption.setDate(date);
            waterConsumption.setUnit("ml");
        }

        waterConsumption.setAmount(waterLogDTO.getSummary().getWater());

        waterConsumptionRepository.save(waterConsumption);
    }

}