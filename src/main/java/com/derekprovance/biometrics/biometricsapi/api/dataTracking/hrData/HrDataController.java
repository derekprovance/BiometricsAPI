package com.derekprovance.biometrics.biometricsapi.api.dataTracking.hrData;

import com.derekprovance.biometrics.biometricsapi.api.rangeEntity.AbstractRangeEntityApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/hr-data")
public class HrDataController extends AbstractRangeEntityApi {
    private final HrDataRepository hrDataRepository;

    @Autowired
    public HrDataController(HrDataRepository hrDataRepository) {
        this.hrDataRepository = hrDataRepository;
    }

    @PostMapping("")
    public HrData newHrDataEntry(@RequestBody HrData newEntry) {
        newEntry.setDatetime(LocalDateTime.now());

        return hrDataRepository.save(newEntry);
    }

    protected HrDataRepository getRepository() {
        return this.hrDataRepository;
    }
}
