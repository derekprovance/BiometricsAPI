package com.derekprovance.biometrics.biometricsapi.api.utility.fitbit;

import com.derekprovance.biometrics.biometricsapi.api.AbstractApiController;
import com.derekprovance.biometrics.biometricsapi.services.sync.fitbit.FitBitAccessTokenService;
import com.derekprovance.biometrics.biometricsapi.services.sync.FoodLogService;
import com.derekprovance.biometrics.biometricsapi.services.sync.WaterLogService;
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

import javax.security.auth.login.CredentialException;
import javax.websocket.server.PathParam;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/fitbit")
public class FitBitController extends AbstractApiController {

    private final Boolean fitBitEnabled;
    private final WaterLogService waterLogService;
    private final FoodLogService foodLogService;
    private final FitBitAccessTokenService fitBitAccessTokenService;

    @Autowired
    public FitBitController(@Value("${fitbit.enabled}") Boolean fitBitEnabled, WaterLogService waterLogService, FoodLogService foodLogService, FitBitAccessTokenService fitBitAccessTokenService) {
        this.fitBitEnabled = fitBitEnabled;
        this.waterLogService = waterLogService;
        this.foodLogService = foodLogService;
        this.fitBitAccessTokenService = fitBitAccessTokenService;
    }

    @RequestMapping(value = "/sync/water-consumption/{date}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> pullWaterByDate(
            @PathVariable(value="date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) throws CredentialException {
        Map<String, Object> map = new HashMap<>();

        if(!fitBitEnabled) {
            map.put("status", HttpStatus.BAD_REQUEST.value());
            map.put("message", "FitBit API integration has been disabled.");
            return ResponseEntity.badRequest().body(gson.toJson(map));
        }

        waterLogService.syncWithDatabase(date);

        map.put("status", HttpStatus.OK.value());
        map.put("message", "Processed water log entry for date " + date.toString());

        return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(map));

    }

    @RequestMapping(value = "/sync/meal/{date}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> pullMealsFromFitbitByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) throws CredentialException {
        Integer count = foodLogService.syncWithDatabase(date);

        Map<String, Object> map = new HashMap<>();
        map.put("status", HttpStatus.OK.value());
        map.put("message", String.format("Processed %d entities for date %s", count, date.toString()));

        return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(map));
    }

    @RequestMapping(value = "/authorize", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> authorizeFitBit(
            @PathParam("code") String code
    ) throws CredentialException {
        fitBitAccessTokenService.updateRefreshToken(code);

        Map<String, Object> map = new HashMap<>();
        map.put("status", HttpStatus.OK.value());
        map.put("message", "Refresh Token has been updated successfully");
        map.put("code", code);

        return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(map));
    }
}
