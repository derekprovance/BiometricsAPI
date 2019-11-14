package com.derekprovance.biometrics.biometricsapi.api.physio.heartRate;

import com.derekprovance.biometrics.biometricsapi.api.AbstractBioDateTimeMultipleEntityApi;
import com.derekprovance.biometrics.biometricsapi.database.entity.HeartRate;
import com.derekprovance.biometrics.biometricsapi.database.repository.HeartRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/hr-data")
public class HeartRateController extends AbstractBioDateTimeMultipleEntityApi {
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
