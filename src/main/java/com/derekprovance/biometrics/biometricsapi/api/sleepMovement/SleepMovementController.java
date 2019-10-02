package com.derekprovance.biometrics.biometricsapi.api.sleepMovement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
public class SleepMovementController {
    @Autowired
    private SleepMovementRepository sleepMovementRepository;

    @GetMapping("/sleep-movement/")
    public Iterable<SleepMovement> getSleepMovementData() {
        return sleepMovementRepository.findAll();
    }

    @GetMapping("/sleep-movement/{id}")
    public SleepMovement getSingleSleepMovementDataEntry(@PathVariable Integer id) {
        return sleepMovementRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id.toString()));
    }

    @PostMapping("/sleep-movement")
    public SleepMovement newSleepMovementDataEntry(@RequestBody SleepMovement newEntry) {
        return sleepMovementRepository.save(newEntry);
    }
}
