package com.derekprovance.biometrics.biometricsapi.api.dataTracking.bloodSugar;

import com.derekprovance.biometrics.biometricsapi.api.dataTracking.AbstractDataTrackingApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RestController
@RequestMapping("/blood-sugar")
public class BloodSugarController extends AbstractDataTrackingApi {
    private final BloodSugarRepository bloodSugarRepository;

    @Autowired
    public BloodSugarController(BloodSugarRepository bloodSugarRepository) {
        this.bloodSugarRepository = bloodSugarRepository;
    }

    @PostMapping("")
    public BloodSugar newBloodSugarEntry(@RequestBody BloodSugar newEntry) {
        if(newEntry.getDatetime() == null) {
            newEntry.setDatetime(LocalDateTime.now());
        }
        return bloodSugarRepository.save(newEntry);
    }

    @RequestMapping(value="/date/{date}", method=RequestMethod.GET)
    public Iterable<?> getBloodSugarByDate(
            @PathVariable(value="date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return getRepository().findByDatetimeBetween(date.atStartOfDay(), date.atTime(LocalTime.MAX));
    }

    @RequestMapping(value="/date/{startDate}/{endDate}", method=RequestMethod.GET)
    public Iterable<?> getBloodSugarBetweenDate(
            @PathVariable(value="startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @PathVariable(value="endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        return getRepository().findByDatetimeBetween(startDate.atStartOfDay(), endDate.atTime(LocalTime.MAX));
    }

    protected BloodSugarRepository getRepository() {
        return this.bloodSugarRepository;
    }
}
