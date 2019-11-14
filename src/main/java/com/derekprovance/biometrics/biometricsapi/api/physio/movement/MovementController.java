package com.derekprovance.biometrics.biometricsapi.api.physio.movement;

import com.derekprovance.biometrics.biometricsapi.api.AbstractBioDateTimeMultipleEntityApi;
import com.derekprovance.biometrics.biometricsapi.database.entity.Movement;
import com.derekprovance.biometrics.biometricsapi.database.repository.MovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/movement-data")
public class MovementController extends AbstractBioDateTimeMultipleEntityApi {
    private final MovementRepository movementRepository;

    @Autowired
    public MovementController(MovementRepository movementRepository) {
        this.movementRepository = movementRepository;
    }

    @PostMapping("")
    public Movement newMovementDataEntry(@RequestBody Movement newEntry) {
        if(newEntry.getDatetime() == null) {
            newEntry.setDatetime(LocalDateTime.now());
        }

        return movementRepository.save(newEntry);
    }

    protected MovementRepository getRepository() {
        return this.movementRepository;
    }
}
