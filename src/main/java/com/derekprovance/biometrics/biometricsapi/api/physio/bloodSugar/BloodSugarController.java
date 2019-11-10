package com.derekprovance.biometrics.biometricsapi.api.physio.bloodSugar;

import com.derekprovance.biometrics.biometricsapi.api.genericEntities.range.AbstractDateTimeMultipleEntityApi;
import com.derekprovance.biometrics.biometricsapi.api.genericEntities.range.BaseRangeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/blood-sugar")
public class BloodSugarController extends AbstractDateTimeMultipleEntityApi {
    private final BloodSugarRepository bloodSugarRepository;

    @Autowired
    public BloodSugarController(BloodSugarRepository bloodSugarRepository) {
        this.bloodSugarRepository = bloodSugarRepository;
    }

    @PostMapping("")
    public BaseRangeEntity newEntry(@RequestBody BloodSugar newEntry) {
        if(newEntry.getDatetime() == null) {
            newEntry.setDatetime(LocalDateTime.now());
        }
        return bloodSugarRepository.save(newEntry);
    }

    protected BloodSugarRepository getRepository() {
        return this.bloodSugarRepository;
    }
}
