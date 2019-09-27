package com.derekprovance.biometrics.biometricsapi.sleep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class SleepController {
    @Autowired
    private SleepRepository sleepRepository;

    @RequestMapping(name="/sleep/", method=GET)
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
