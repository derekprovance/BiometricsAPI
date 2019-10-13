package com.derekprovance.biometrics.biometricsapi.api.waterConsumptionData;

import com.derekprovance.biometrics.biometricsapi.api.AbstractApiController;
import com.derekprovance.biometrics.biometricsapi.services.fitbit.WaterLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@RestController
public class WaterConsumptionController extends AbstractApiController {
    private WaterConsumptionRepository waterConsumptionRepository;
    private WaterLogService waterLogService;
    private Boolean fitBitEnabled;

    @Autowired
    public WaterConsumptionController(
            WaterConsumptionRepository waterConsumptionRepository,
            WaterLogService waterLogService,
            @Value("${fitbit.enabled}") Boolean fitBitEnabled
    ) {
        this.waterConsumptionRepository = waterConsumptionRepository;
        this.waterLogService = waterLogService;
        this.fitBitEnabled = fitBitEnabled;
    }

    @RequestMapping(value="/water-consumption/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getSingleWaterConsumptionEntry(@PathVariable Integer id) {
        try {
            final WaterConsumption waterConsumption = waterConsumptionRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Not Found: " + id.toString()));

            return ResponseEntity.ok().body(gson.toJson(waterConsumption));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/water-consumption/fitbit/{dateStr}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
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

    @PostMapping("/water-consumption")
    public WaterConsumption newWaterConsumptionDataEntry(@RequestBody WaterConsumption newEntry) {
        return waterConsumptionRepository.save(newEntry);
    }

    @RequestMapping(value="/water-consumption/date/{startDate}", method=RequestMethod.GET)
    public Iterable<WaterConsumption> getWaterConsumptionByDate(
            @PathVariable(value="startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return waterConsumptionRepository.findAllByDateBetween(date.atStartOfDay(), date.atTime(LocalTime.MAX));
    }

    @RequestMapping(value="/water-consumption/date/{startDate}/{endDate}", method=RequestMethod.GET)
    public Iterable<WaterConsumption> getWaterConsumptionBetweenDate(
            @PathVariable(value="startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @PathVariable(value="endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        return waterConsumptionRepository.findAllByDateBetween(startDate.atStartOfDay(), endDate.atTime(LocalTime.MAX));
    }
}
