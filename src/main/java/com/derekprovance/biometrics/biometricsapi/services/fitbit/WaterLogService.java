package com.derekprovance.biometrics.biometricsapi.services.fitbit;

import com.derekprovance.biometrics.biometricsapi.api.waterConsumptionData.WaterConsumption;
import com.derekprovance.biometrics.biometricsapi.api.waterConsumptionData.WaterConsumptionRepository;
import com.derekprovance.biometrics.biometricsapi.services.fitbit.DTO.water.WaterLogDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialException;
import java.time.LocalDate;

@Service
public class WaterLogService {

    private static final Logger log = LoggerFactory.getLogger(WaterLogService.class);

    private final WaterConsumptionRepository waterConsumptionRepository;
    private final FitBitAPIService fitbitAPIService;

    @Autowired
    public WaterLogService(WaterConsumptionRepository waterConsumptionRepository, FitBitAPIService fitbitAPIService) {
        this.waterConsumptionRepository = waterConsumptionRepository;
        this.fitbitAPIService = fitbitAPIService;
    }

    public void syncWithDatabase() throws CredentialException {
        syncWithDatabase(LocalDate.now());
    }

    public void syncWithDatabase(LocalDate date) throws CredentialException {
        final WaterLogDTO entriesForDate = fitbitAPIService.performWaterLog(date);

        if(entriesForDate != null) {
            processEntries(date, entriesForDate);
        }
    }

    private void processEntries(LocalDate date, WaterLogDTO waterLogDTO) {
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