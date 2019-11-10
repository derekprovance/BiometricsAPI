package com.derekprovance.biometrics.biometricsapi.api.physio.heartRate;

import com.derekprovance.biometrics.biometricsapi.api.genericEntities.datetime.AbstractDateTimeMultipleEntityApi;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/hr-data")
public class HeartRateController extends AbstractDateTimeMultipleEntityApi {
    private final HeartRateRepository heartRateRepository;

    @Autowired
    public HeartRateController(HeartRateRepository heartRateRepository) {
        this.heartRateRepository = heartRateRepository;
    }

    @PostMapping("")
    public HeartRate newHrDataEntry(@RequestBody HeartRate newEntry) {
        if(newEntry.getDatetime() == null) {
            newEntry.setDatetime(LocalDateTime.now());
        }

        return heartRateRepository.save(newEntry);
    }

    protected HeartRateRepository getRepository() {
        return this.heartRateRepository;
    }
}
