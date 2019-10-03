package com.derekprovance.biometrics.biometricsapi.api.waterConsumptionData;

import com.derekprovance.biometrics.biometricsapi.services.fitbit.WaterLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class WaterConsumptionController {
    private WaterConsumptionRepository waterConsumptionRepository;
    private WaterLogService waterLogService;

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    public WaterConsumptionController(WaterConsumptionRepository waterConsumptionRepository, WaterLogService waterLogService) {
        this.waterConsumptionRepository = waterConsumptionRepository;
        this.waterLogService = waterLogService;
    }

    @GetMapping("/water-consumption/")
    public Iterable<WaterConsumption> getWaterConsumptionData() {
        return waterConsumptionRepository.findAll();
    }

    @GetMapping("/water-consumption/{id}")
    public WaterConsumption getSingleWaterConsumptionDataEntry(@PathVariable Integer id) {
        return waterConsumptionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id.toString()));
    }

    @RequestMapping(value = "/water-consumption/fitbit/{dateStr}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> pullWaterByDate(@PathVariable String dateStr) {
        try {
            Date date = simpleDateFormat.parse(dateStr);
            waterLogService.syncWithDatabase(date);

            return ResponseEntity.status(HttpStatus.OK).body(String.format("{\"status\": \"%s\", \"message\": \"Processed water log entry for date %s\"}", HttpStatus.OK, dateStr));
        } catch (ParseException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(String.format("{\"status\": \"%s\", \"message\": \"Date format must be YYYY-MM-DD\"}", HttpStatus.BAD_REQUEST));
        }
    }
    
    @PostMapping("/water-consumption")
    public WaterConsumption newWaterConsumptionDataEntry(@RequestBody WaterConsumption newEntry) {
        return waterConsumptionRepository.save(newEntry);
    }
}
