package com.derekprovance.biometrics.biometricsapi.api.physio.movement;

import com.derekprovance.biometrics.biometricsapi.api.genericEntities.range.AbstractRangeEntityApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/movement-data")
public class MovementController extends AbstractRangeEntityApi {
    private final MovementRepository movementRepository;

    @Autowired
    public MovementController(MovementRepository movementRepository) {
        this.movementRepository = movementRepository;
    }

    @PostMapping("")
    public Movement newMovementDataEntry(@RequestBody Movement newEntry) {
        newEntry.setDatetime(LocalDateTime.now());

        return movementRepository.save(newEntry);
    }

    protected MovementRepository getRepository() {
        return this.movementRepository;
    }
}
