package com.derekprovance.biometrics.biometricsapi.api.fitbit;

import com.derekprovance.biometrics.biometricsapi.api.AbstractApiController;
import com.derekprovance.biometrics.biometricsapi.services.fitbit.FitBitAccessTokenService;
import com.derekprovance.biometrics.biometricsapi.services.fitbit.FoodLogService;
import com.derekprovance.biometrics.biometricsapi.services.fitbit.WaterLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
public class FitBitController extends AbstractApiController {

    private Boolean fitBitEnabled;
    private WaterLogService waterLogService;
    private FoodLogService foodLogService;
    private FitBitAccessTokenService fitBitAccessTokenService;

    @Autowired
    public FitBitController(@Value("${fitbit.enabled}") Boolean fitBitEnabled, WaterLogService waterLogService, FoodLogService foodLogService, FitBitAccessTokenService fitBitAccessTokenService) {
        this.fitBitEnabled = fitBitEnabled;
        this.waterLogService = waterLogService;
        this.foodLogService = foodLogService;
        this.fitBitAccessTokenService = fitBitAccessTokenService;
    }

    @RequestMapping(value = "/fitbit/sync/water-consumption/{dateStr}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> pullWaterByDate(
            @PathVariable(value="date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        Map<String, Object> map = new HashMap<>();

        if(!fitBitEnabled) {
            map.put("status", HttpStatus.BAD_REQUEST.value());
            map.put("message", "FitBit API access has been disabled.");
            return ResponseEntity.badRequest().body(gson.toJson(map));
        }

        waterLogService.syncWithDatabase(date);

        map.put("status", HttpStatus.OK.value());
        map.put("message", "Processed water log entry for date " + date.toString());

        return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(map));

    }

    @RequestMapping(value = "/fitbit/sync/meal/{date}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> pullMealsFromFitbitByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        Integer count = foodLogService.syncWithDatabase(date);

        Map<String, Object> map = new HashMap<>();
        map.put("status", HttpStatus.OK.value());
        map.put("message", String.format("Processed %d entities for date %s", count, date.toString()));

        return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(map));
    }

    @RequestMapping(value = "/fitbit/authorize", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> authorizeFitBit(
            @PathParam("code") String code
    ) {
        fitBitAccessTokenService.updateRefreshToken(code);

        Map<String, Object> map = new HashMap<>();
        map.put("status", HttpStatus.OK.value());
        map.put("message", "Refresh Token has been updated successfully");
        map.put("code", code);

        return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(map));
    }
}
