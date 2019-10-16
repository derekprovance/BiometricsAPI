package com.derekprovance.biometrics.biometricsapi.api.sleep;

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
public class SleepController extends AbstractApiController {
    private SleepRepository sleepRepository;

    @Autowired
    public SleepController(SleepRepository sleepRepository) {
        this.sleepRepository = sleepRepository;
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

    @RequestMapping(value="/sleep/date/{date}", method=RequestMethod.GET)
    public Iterable<Sleep> getSleepDataByDate(
            @PathVariable(value="date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return sleepRepository.findAllBySleepStartBetween(date.atStartOfDay(), date.atTime(LocalTime.MAX));
    }

    @RequestMapping(value="/sleep/date/{startDate}/{endDate}", method=RequestMethod.GET)
    public Iterable<Sleep> getSleepDataBetweenDate(
            @PathVariable(value="startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @PathVariable(value="endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        return sleepRepository.findAllBySleepStartBetween(startDate.atStartOfDay(), endDate.atTime(LocalTime.MAX));
    }
}
