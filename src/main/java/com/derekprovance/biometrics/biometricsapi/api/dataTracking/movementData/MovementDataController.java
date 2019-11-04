package com.derekprovance.biometrics.biometricsapi.api.dataTracking.movementData;

import com.derekprovance.biometrics.biometricsapi.api.rangeEntity.AbstractRangeEntityApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/movement-data")
public class MovementDataController extends AbstractRangeEntityApi {
    private final MovementDataRepository movementDataRepository;

    @Autowired
    public MovementDataController(MovementDataRepository movementDataRepository) {
        this.movementDataRepository = movementDataRepository;
    }

    @PostMapping("")
    public MovementData newMovementDataEntry(@RequestBody MovementData newEntry) {
        newEntry.setDatetime(LocalDateTime.now());

        return movementDataRepository.save(newEntry);
    }

    protected MovementDataRepository getRepository() {
        return this.movementDataRepository;
    }
}
