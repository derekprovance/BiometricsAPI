package com.derekprovance.biometrics.biometricsapi.api.sleep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
public class SleepController {
    private SleepRepository sleepRepository;

    @Autowired
    public SleepController(SleepRepository sleepRepository) {
        this.sleepRepository = sleepRepository;
    }

    @GetMapping("/sleep/")
    public Iterable<Sleep> getSleepData() {
        return sleepRepository.findAll();
    }

    @GetMapping("/sleep/{id}")
    public Sleep getSingleSleepDataEntry(@PathVariable Integer id) {
        return sleepRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id.toString()));
    }

    @PostMapping("/sleep")
    public Sleep newSleepDataEntry(@RequestBody Sleep newEntry) {
        return sleepRepository.save(newEntry);
    }
}
