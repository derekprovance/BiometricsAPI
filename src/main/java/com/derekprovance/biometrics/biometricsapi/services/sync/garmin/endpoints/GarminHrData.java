package com.derekprovance.biometrics.biometricsapi.services.sync.garmin.endpoints;

import com.derekprovance.biometrics.biometricsapi.database.entity.HeartRate;
import com.derekprovance.biometrics.biometricsapi.database.repository.HeartRateRepository;
import com.derekprovance.biometrics.biometricsapi.services.AbstractService;
import com.derekprovance.biometrics.biometricsapi.services.sync.garmin.DTO.DailyHeartRate;
import com.derekprovance.biometrics.biometricsapi.services.sync.garmin.GarminApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class GarminHrData extends AbstractService {
    private final HeartRateRepository heartRateRepository;
    private final GarminApiService garminApiService;

    @Autowired
    public GarminHrData(HeartRateRepository heartRateRepository, GarminApiService garminApiService) {
        this.heartRateRepository = heartRateRepository;
        this.garminApiService = garminApiService;
    }

    public List<HeartRate> syncHrData(LocalDate date) throws CredentialNotFoundException {
        final DailyHeartRate dailyHrData = garminApiService.getDailyHrData(date);
        List<HeartRate> hrData = new ArrayList<>();
        Object[][] heartRateValues = dailyHrData.getHeartRateValues();

        if(heartRateValues != null) {
            for (Object[] heartRateValue : heartRateValues) {
                HeartRate newEntry = new HeartRate();
                newEntry.setDatetime(convertTimestamp((Long) heartRateValue[0]));
                newEntry.setHrValue((Integer) heartRateValue[1]);
                hrData.add(newEntry);
            }

            heartRateRepository.saveAll(hrData);
        }

        return hrData;
    }
}
