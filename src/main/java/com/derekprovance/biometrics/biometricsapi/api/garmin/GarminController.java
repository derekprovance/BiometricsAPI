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

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity<String> getGarminData(@PathVariable  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        if(!garminEnabled) {
            Map<String, Object> map = new HashMap<>();
            map.put("status", HttpStatus.BAD_REQUEST.value());
            map.put("message", "Garmin API access has been disabled.");
            return ResponseEntity.badRequest().body(gson.toJson(map));
        }

        final ItemSyncCount syncCount = garminSyncService.sync(date);

        if(itemsWereSynced(syncCount)) {
            return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(syncCount));
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("status", HttpStatus.OK.value());
            map.put("message", "No items available for sync");
            return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(map));
        }
    }

    private Boolean itemsWereSynced(ItemSyncCount itemSyncCount) {
        if(itemSyncCount == null) {
            return false;
        }

        return itemSyncCount.getHrData() > 0 ||
                itemSyncCount.getMovementData() > 0 ||
                itemSyncCount.getSleepMovementData() > 0;
    }

}
