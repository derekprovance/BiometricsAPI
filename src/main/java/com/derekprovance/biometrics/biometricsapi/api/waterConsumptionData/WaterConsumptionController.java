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
import java.text.ParseException;
import java.util.Date;

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
    public ResponseEntity<String> pullWaterByDate(@PathVariable String dateStr) {
        if(!fitBitEnabled) {
            return ResponseEntity.badRequest().body(String.format("{\"status\": \"%s\", \"message\": \"FitBit API access has been disabled.\"}", HttpStatus.BAD_REQUEST));
        }

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

    @RequestMapping(value="/water-consumption/date/{startDate}", method=RequestMethod.GET)
    public Iterable<WaterConsumption> getWaterConsumptionByDate(
            @PathVariable(value="startDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date date
    ) {
        return waterConsumptionRepository.findAllByDateBetween(getBeginningOfDay(date), getEndOfDay(date));
    }

    @RequestMapping(value="/water-consumption/date/{startDate}/{endDate}", method=RequestMethod.GET)
    public Iterable<WaterConsumption> getWaterConsumptionBetweenDate(
            @PathVariable(value="startDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
            @PathVariable(value="endDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate
    ) {
        return waterConsumptionRepository.findAllByDateBetween(getBeginningOfDay(startDate), getEndOfDay(endDate));
    }
}
