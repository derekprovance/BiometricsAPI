package com.derekprovance.biometrics.biometricsapi.api.physio.heartRate;

import com.derekprovance.biometrics.biometricsapi.api.generic.datetime.AbstractDateTimeMultipleEntityApi;
import com.derekprovance.biometrics.biometricsapi.database.entity.HeartRate;
import com.derekprovance.biometrics.biometricsapi.database.repository.HeartRateRepositoryGeneric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/hr-data")
public class HeartRateController extends AbstractDateTimeMultipleEntityApi {
    private final HeartRateRepositoryGeneric heartRateRepository;

    @Autowired
    public HeartRateController(HeartRateRepositoryGeneric heartRateRepository) {
        this.heartRateRepository = heartRateRepository;
    }

    @PostMapping("")
    public HeartRate newHrDataEntry(@RequestBody HeartRate newEntry) {
        if(newEntry.getDatetime() == null) {
            newEntry.setDatetime(LocalDateTime.now());
        }

        return heartRateRepository.save(newEntry);
    }

    protected HeartRateRepositoryGeneric getRepository() {
        return this.heartRateRepository;
    }
}
