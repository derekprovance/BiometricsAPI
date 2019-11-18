package com.derekprovance.biometrics.biometricsapi.api.utility.garmin;

import com.derekprovance.biometrics.biometricsapi.api.AbstractApiController;
import com.derekprovance.biometrics.biometricsapi.services.sync.garmin.GarminSyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.CredentialNotFoundException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/garmin")
public class GarminController extends AbstractApiController {

    private final GarminSyncService garminSyncService;
    private final Boolean garminEnabled;

    @Autowired
    public GarminController(
            GarminSyncService garminSyncService,
            @Value("${garmin.enabled}") Boolean garminEnabled
    ) {
        this.garminSyncService = garminSyncService;
        this.garminEnabled = garminEnabled;

    }

    @RequestMapping(value = "/sync/{date}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getGarminData(@PathVariable  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) throws CredentialNotFoundException {
        if(!garminEnabled) {
            return ResponseEntity.badRequest().body(gson.toJson(getDisabledJsonMap()));
        }

        final ItemSyncCount syncCount = garminSyncService.sync(date);

        return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(syncCount));
    }

    @RequestMapping(value = "/sync/{start}/{end}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getGarminDataWithRange(
            @PathVariable  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @PathVariable  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end
    ) throws CredentialNotFoundException {
        if(!garminEnabled) {
            return ResponseEntity.badRequest().body(gson.toJson(getDisabledJsonMap()));
        }

        final ItemSyncCount syncCount = garminSyncService.sync(start, end);

        return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(syncCount));
    }

    private Map<String, Object> getDisabledJsonMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("status", HttpStatus.BAD_REQUEST.value());
        map.put("message", "Garmin API integration has been disabled.");
        return map;
    }
}
