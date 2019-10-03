package com.derekprovance.biometrics.biometricsapi.api.bloodSugar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
public class BloodSugarController {
    @Autowired
    private BloodSugarRepository bloodSugarRepository;
    private final java.util.Date dt = new java.util.Date();
    private final java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @GetMapping("/blood-sugar-entries/")
    public Iterable<BloodSugar> getBloodSugarEntries() {
        return bloodSugarRepository.findAll();
    }

    @GetMapping("/blood-sugar-entries/{id}")
    public BloodSugar getSingleBloodSugarEntry(@PathVariable Integer id) {
        return bloodSugarRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id.toString()));
    }

    @PostMapping("/blood-sugar-entries")
    public BloodSugar newBloodSugarEntry(@RequestBody BloodSugar newEntry) {
        newEntry.setDatetime(sdf.format(dt));

        return bloodSugarRepository.save(newEntry);
    }
}
