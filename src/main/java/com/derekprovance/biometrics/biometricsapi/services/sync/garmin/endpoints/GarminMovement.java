package com.derekprovance.biometrics.biometricsapi.services.sync.garmin.endpoints;

import com.derekprovance.biometrics.biometricsapi.database.entity.Movement;
import com.derekprovance.biometrics.biometricsapi.database.repository.MovementRepository;
import com.derekprovance.biometrics.biometricsapi.services.AbstractService;
import com.derekprovance.biometrics.biometricsapi.services.sync.garmin.DTO.DailyMovementData;
import com.derekprovance.biometrics.biometricsapi.services.sync.garmin.GarminApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class GarminMovement extends AbstractService {
    private final MovementRepository movementRepository;
    private final GarminApiService garminApiService;

    @Autowired
    public GarminMovement(MovementRepository movementRepository, GarminApiService garminApiService) {
        this.movementRepository = movementRepository;
        this.garminApiService = garminApiService;
    }

    public List<Movement> syncMovementData(LocalDate date) throws CredentialNotFoundException {
        final DailyMovementData dailyMovement = garminApiService.getDailyMovement(date);
        Object[][] dailyMovementValues = dailyMovement.getMovementValues();
        List<Movement> movementData = new ArrayList<>();

        if(dailyMovementValues != null) {
            for(Object[] dailyMovementValue : dailyMovementValues) {
                Movement movementEntry = new Movement();
                movementEntry.setDatetime(convertTimestamp((Long) dailyMovementValue[0]));
                movementEntry.setMovement((Double) dailyMovementValue[1]);
                movementData.add(movementEntry);
            }

            movementRepository.saveAll(movementData);
        }

        return movementData;
    }
}
