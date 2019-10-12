package com.derekprovance.biometrics.biometricsapi.services.fitbit;

import com.derekprovance.biometrics.biometricsapi.api.waterConsumptionData.WaterConsumption;
import com.derekprovance.biometrics.biometricsapi.api.waterConsumptionData.WaterConsumptionRepository;
import com.derekprovance.biometrics.biometricsapi.services.fitbit.DTO.water.WaterLogDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
        WaterConsumption waterConsumption = waterConsumptionRepository.findByDate(date);

        if(waterConsumption == null) {
            waterConsumption = new WaterConsumption();
            waterConsumption.setDate(date);
            waterConsumption.setUnit("ml");
        }

        waterConsumption.setAmount(waterLogDTO.getSummary().getWater());

        waterConsumptionRepository.save(waterConsumption);
    }

}