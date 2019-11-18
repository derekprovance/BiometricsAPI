package com.derekprovance.biometrics.biometricsapi.services.sync.garmin.endpoints;

import com.derekprovance.biometrics.biometricsapi.database.entity.MovementData;
import com.derekprovance.biometrics.biometricsapi.database.repository.GenericCrudDateTimeRepository;
import com.derekprovance.biometrics.biometricsapi.database.repository.MovementRepository;
import com.derekprovance.biometrics.biometricsapi.services.sync.garmin.DTO.DailyMovementData;
import com.derekprovance.biometrics.biometricsapi.services.sync.garmin.GarminApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class GarminMovement extends AbstractEndpoint {
    private final MovementRepository movementRepository;
    private final GarminApiService garminApiService;

    @Autowired
    public GarminMovement(MovementRepository movementRepository, GarminApiService garminApiService) {
        this.movementRepository = movementRepository;
        this.garminApiService = garminApiService;
    }

    public List<MovementData> syncMovementData(LocalDate date) throws CredentialNotFoundException {
        final DailyMovementData dailyMovement = garminApiService.getDailyMovement(date);
        Object[][] dailyMovementValues = dailyMovement.getMovementValues();
        List<LocalDateTime> existingDates = getListOfDateTimeEntries(date);

        List<MovementData> movementData = new ArrayList<>();
        for(Object[] dailyMovementValue : dailyMovementValues) {
            LocalDateTime newEntryDateTime = convertTimestamp((Long) dailyMovementValue[0]);

            if(!existingDates.contains(newEntryDateTime)) {
                MovementData movementDataEntry = new MovementData();
                movementDataEntry.setDatetime(newEntryDateTime);
                movementDataEntry.setMovement((Double) dailyMovementValue[1]);
                movementData.add(movementDataEntry);
            }
        }

        movementRepository.saveAll(movementData);

        return movementData;
    }

    protected GenericCrudDateTimeRepository getRepository() {
        return this.movementRepository;
    }
}
