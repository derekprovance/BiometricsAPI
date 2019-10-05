package com.derekprovance.biometrics.biometricsapi.api.sleepMovement;

import com.derekprovance.biometrics.biometricsapi.api.AbstractApiController;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
public class SleepMovementController extends AbstractApiController {
    private SleepMovementRepository sleepMovementRepository;
    private final Gson gson = new Gson();

    @Autowired
    public SleepMovementController(SleepMovementRepository sleepMovementRepository) {
        this.sleepMovementRepository = sleepMovementRepository;
    }

    @RequestMapping(value="/sleep-movement/{id}", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getSingleSleepMovementEntry(@PathVariable Integer id) {
        try {
            final SleepMovement sleepMovement = sleepMovementRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Not Found: " + id.toString()));

            return ResponseEntity.ok().body(gson.toJson(sleepMovement));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/sleep-movement")
    public SleepMovement newSleepMovementDataEntry(@RequestBody SleepMovement newEntry) {
        return sleepMovementRepository.save(newEntry);
    }
}
