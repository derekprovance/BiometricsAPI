package com.derekprovance.biometrics.biometricsapi.api.waterConsumptionData;

import com.derekprovance.biometrics.biometricsapi.api.AbstractApiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;

@RestController
public class WaterConsumptionController extends AbstractApiController {
    private WaterConsumptionRepository waterConsumptionRepository;

    @Autowired
    public WaterConsumptionController(
            WaterConsumptionRepository waterConsumptionRepository
    ) {
        this.waterConsumptionRepository = waterConsumptionRepository;
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

    @PostMapping("/water-consumption")
    public WaterConsumption newWaterConsumptionDataEntry(@RequestBody WaterConsumption newEntry) {
        return waterConsumptionRepository.save(newEntry);
    }

    @RequestMapping(value="/water-consumption/date/{date}", method=RequestMethod.GET)
    public Iterable<WaterConsumption> getWaterConsumptionByDate(
            @PathVariable(value="date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return waterConsumptionRepository.findAllByDateBetween(date, date);
    }

    @RequestMapping(value="/water-consumption/date/{startDate}/{endDate}", method=RequestMethod.GET)
    public Iterable<WaterConsumption> getWaterConsumptionBetweenDate(
            @PathVariable(value="startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @PathVariable(value="endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        return waterConsumptionRepository.findAllByDateBetween(startDate, endDate);
    }
}
