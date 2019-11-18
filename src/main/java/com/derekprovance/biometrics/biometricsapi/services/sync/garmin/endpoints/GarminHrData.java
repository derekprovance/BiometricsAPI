package com.derekprovance.biometrics.biometricsapi.services.sync.garmin.endpoints;

import com.derekprovance.biometrics.biometricsapi.database.entity.HeartRate;
import com.derekprovance.biometrics.biometricsapi.database.repository.GenericCrudDateTimeRepository;
import com.derekprovance.biometrics.biometricsapi.database.repository.HeartRateRepository;
import com.derekprovance.biometrics.biometricsapi.services.sync.garmin.DTO.DailyHeartRate;
import com.derekprovance.biometrics.biometricsapi.services.sync.garmin.GarminApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class GarminHrData extends AbstractEndpoint {
    private final HeartRateRepository heartRateRepository;
    private final GarminApiService garminApiService;

    @Autowired
    public GarminHrData(HeartRateRepository heartRateRepository, GarminApiService garminApiService) {
        this.heartRateRepository = heartRateRepository;
        this.garminApiService = garminApiService;
    }

    public List<HeartRate> syncHrData(LocalDate date) throws CredentialNotFoundException {
        final DailyHeartRate dailyHrDataFromGarmin = garminApiService.getDailyHrData(date);
        Object[][] heartRateValuesFromGarmin = Objects.requireNonNullElse(dailyHrDataFromGarmin.getHeartRateValues(), new Object[][]{});
        List<LocalDateTime> existingHrDates = getListOfDateTimeEntries(date);

        List<HeartRate> hrData = new ArrayList<>();
        for (Object[] heartRateValue : heartRateValuesFromGarmin) {
            LocalDateTime newEntryDateTime = convertTimestamp((Long) heartRateValue[0]);

            if(!existingHrDates.contains(newEntryDateTime)) {
                HeartRate newEntry = new HeartRate();
                newEntry.setDatetime(newEntryDateTime);
                newEntry.setHrValue((Integer) heartRateValue[1]);
                hrData.add(newEntry);
            }
        }

        heartRateRepository.saveAll(hrData);

        return hrData;
    }

    protected GenericCrudDateTimeRepository getRepository() {
        return this.heartRateRepository;
    }
}
