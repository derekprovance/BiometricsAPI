package com.derekprovance.biometrics.biometricsapi.api.sleep;

import com.derekprovance.biometrics.biometricsapi.api.AbstractApiController;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
public class SleepController extends AbstractApiController {
    private SleepRepository sleepRepository;

    @Autowired
    public SleepController(SleepRepository sleepRepository) {
        this.sleepRepository = sleepRepository;
    }

    @GetMapping("/sleep/")
    public Iterable<Sleep> getSleepData() {
        return sleepRepository.findAll();
    }

    @RequestMapping(value="/sleep/{id}", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getSingleSleepEntry(@PathVariable Integer id) {
        try {
            final Sleep sleep = sleepRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Not Found: " + id.toString()));

            return ResponseEntity.ok().body(gson.toJson(sleep));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/sleep")
    public Sleep newSleepDataEntry(@RequestBody Sleep newEntry) {
        return sleepRepository.save(newEntry);
    }
}
