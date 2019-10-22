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
@RequestMapping("/water-consumption")
public class WaterConsumptionController extends AbstractApiController {
    private WaterConsumptionRepository waterConsumptionRepository;

    @Autowired
    public WaterConsumptionController(
            WaterConsumptionRepository waterConsumptionRepository
    ) {
        this.waterConsumptionRepository = waterConsumptionRepository;
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getSingleWaterConsumptionEntry(@PathVariable Integer id) {
        try {
            final WaterConsumption waterConsumption = waterConsumptionRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Not Found: " + id.toString()));

            return ResponseEntity.ok().body(gson.toJson(waterConsumption));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public WaterConsumption newWaterConsumptionDataEntry(@RequestBody WaterConsumption newEntry) {
        return waterConsumptionRepository.save(newEntry);
    }

    @RequestMapping(value="/date/{date}", method=RequestMethod.GET)
    public WaterConsumption getWaterConsumptionByDate(
            @PathVariable(value="date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return waterConsumptionRepository.findByDate(date);
    }

    @RequestMapping(value="/date/{startDate}/{endDate}", method=RequestMethod.GET)
    public Iterable<WaterConsumption> getWaterConsumptionBetweenDate(
            @PathVariable(value="startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @PathVariable(value="endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        return waterConsumptionRepository.findAllByDateBetween(startDate, endDate);
    }
}
