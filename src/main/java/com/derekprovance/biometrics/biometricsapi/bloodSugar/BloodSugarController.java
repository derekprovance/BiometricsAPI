package com.derekprovance.biometrics.biometricsapi.bloodSugar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class BloodSugarController {
    @Autowired
    private BloodSugarRepository bloodSugarRepository;
    private java.util.Date dt = new java.util.Date();
    private java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @RequestMapping(name="/bloodSugarEntries/", method=GET)
    public Iterable<BloodSugar> getBloodSugarEntries() {
        return bloodSugarRepository.findAll();
    }

    @GetMapping("/bloodSugarEntries/{id}")
    public BloodSugar getSingleBloodSugarEntry(@PathVariable Integer id) {
        return bloodSugarRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id.toString()));
    }

    @PostMapping("/bloodSugarEntries")
    public BloodSugar newBloodSugarEntry(@RequestBody BloodSugar newEntry) {
        newEntry.setDatetime(sdf.format(dt));

        return bloodSugarRepository.save(newEntry);
    }
}
