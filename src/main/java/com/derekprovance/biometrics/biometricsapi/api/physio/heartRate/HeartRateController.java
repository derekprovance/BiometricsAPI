package com.derekprovance.biometrics.biometricsapi.api.physio.heartRate;

import com.derekprovance.biometrics.biometricsapi.api.genericEntities.range.AbstractRangeEntityApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/hr-data")
public class HeartRateController extends AbstractRangeEntityApi {
    private final HeartRateRepository heartRateRepository;

    @Autowired
    public HeartRateController(HeartRateRepository heartRateRepository) {
        this.heartRateRepository = heartRateRepository;
    }

    @PostMapping("")
    public HeartRate newHrDataEntry(@RequestBody HeartRate newEntry) {
        newEntry.setDatetime(LocalDateTime.now());

        return heartRateRepository.save(newEntry);
    }

    protected HeartRateRepository getRepository() {
        return this.heartRateRepository;
    }
}
