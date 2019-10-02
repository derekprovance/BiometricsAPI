package com.derekprovance.biometrics.biometricsapi.api.hrData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
public class HrDataController {
    @Autowired
    private HrDataRepository hrDataRepository;
    private java.util.Date dt = new java.util.Date();
    private java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @GetMapping("/hr-data/")
    public Iterable<HrData> getHrData() {
        return hrDataRepository.findAll();
    }

    @GetMapping("/hr-data/{id}")
    public HrData getSingleHrDataEntry(@PathVariable Integer id) {
        return hrDataRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id.toString()));
    }

    @PostMapping("/hr-data")
    public HrData newHrDataEntry(@RequestBody HrData newEntry) {
        newEntry.setEventTime(sdf.format(dt));

        return hrDataRepository.save(newEntry);
    }
}
