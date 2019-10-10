package com.derekprovance.biometrics.biometricsapi.api.garmin;

import com.derekprovance.biometrics.biometricsapi.services.garmin.GarminSyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class GarminController {

    private GarminSyncService garminSyncService;

    @Autowired
    public GarminController(GarminSyncService garminSyncService) {
        this.garminSyncService = garminSyncService;
    }

    @GetMapping("/garmin/sync/{date}")
    public ItemSyncCount getGarminData(@PathVariable  @DateTimeFormat(pattern="yyyy-MM-dd") Date date) {
        return garminSyncService.sync(date);
    }

}
