package com.derekprovance.biometrics.biometricsapi.api.garmin;

import com.derekprovance.biometrics.biometricsapi.api.AbstractApiController;
import com.derekprovance.biometrics.biometricsapi.services.garmin.GarminSyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class GarminController extends AbstractApiController {

    private GarminSyncService garminSyncService;
    private Boolean garminEnabled;

    @Autowired
    public GarminController(
            GarminSyncService garminSyncService,
            @Value("${garmin.enabled}") Boolean garminEnabled
    ) {
        this.garminSyncService = garminSyncService;
        this.garminEnabled = garminEnabled;

    }

    @RequestMapping(value = "/garmin/sync/{date}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getGarminData(@PathVariable  @DateTimeFormat(pattern="yyyy-MM-dd") Date date) {
        if(!garminEnabled) {
            return ResponseEntity.badRequest().body(String.format("{\"status\": \"%s\", \"message\": \"Garmin API access has been disabled.\"}", HttpStatus.BAD_REQUEST));

        }

        return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(garminSyncService.sync(date)));
    }

}
