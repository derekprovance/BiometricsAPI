package com.derekprovance.biometrics.biometricsapi.api.sleepMovement;

import com.derekprovance.biometrics.biometricsapi.api.AbstractApiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;

@RestController
@RequestMapping("/sleep-movement")
public class SleepMovementController extends AbstractApiController {
    private SleepMovementRepository sleepMovementRepository;

    @Autowired
    public SleepMovementController(SleepMovementRepository sleepMovementRepository) {
        this.sleepMovementRepository = sleepMovementRepository;
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getSingleSleepMovementEntry(@PathVariable Integer id) {
        try {
            final SleepMovement sleepMovement = sleepMovementRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Not Found: " + id.toString()));

            return ResponseEntity.ok().body(gson.toJson(sleepMovement));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
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
}
