package com.derekprovance.biometrics.biometricsapi.api.physio.sleepMovement;

import com.derekprovance.biometrics.biometricsapi.api.AbstractDataTrackingApi;
import com.derekprovance.biometrics.biometricsapi.database.entity.SleepMovement;
import com.derekprovance.biometrics.biometricsapi.database.repository.SleepMovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;

@RestController
@RequestMapping("/sleep-movement")
public class SleepMovementController extends AbstractDataTrackingApi {
    private final SleepMovementRepository sleepMovementRepository;

    @Autowired
    public SleepMovementController(SleepMovementRepository sleepMovementRepository) {
        this.sleepMovementRepository = sleepMovementRepository;
    }

    @PostMapping("")
    public SleepMovement newSleepMovementDataEntry(@RequestBody SleepMovement newEntry) {
        return sleepMovementRepository.save(newEntry);
    }

    @RequestMapping(value="/date/{date}", method=RequestMethod.GET)
    public Iterable<SleepMovement> getSleepMovementDataByDate(
            @PathVariable(value="date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return sleepMovementRepository.findAllByStartBetween(date.atStartOfDay(), date.atTime(LocalTime.MAX));
    }

    @RequestMapping(value="/date/{startDate}/{endDate}", method=RequestMethod.GET)
    public Iterable<SleepMovement> getSleepMovementDataBetweenDate(
            @PathVariable(value="startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @PathVariable(value="endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        return sleepMovementRepository.findAllByStartBetween(startDate.atStartOfDay(), endDate.atTime(LocalTime.MAX));
    }

    protected SleepMovementRepository getRepository() {
        return this.sleepMovementRepository;
    }
}
