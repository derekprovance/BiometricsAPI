package com.derekprovance.biometrics.biometricsapi.api.dataTracking.waterConsumptionData;

import com.derekprovance.biometrics.biometricsapi.api.AbstractApiController;
import com.derekprovance.biometrics.biometricsapi.api.dataTracking.AbstractDataTrackingApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;

@RestController
@RequestMapping("/water-consumption")
public class WaterConsumptionController extends AbstractDataTrackingApi {
    private final WaterConsumptionRepository waterConsumptionRepository;

    @Autowired
    public WaterConsumptionController(
            WaterConsumptionRepository waterConsumptionRepository
    ) {
        this.waterConsumptionRepository = waterConsumptionRepository;
    }

    @PostMapping("")
    public WaterConsumption newWaterConsumptionDataEntry(@RequestBody WaterConsumption newEntry) {
        return waterConsumptionRepository.save(newEntry);
    }

    @RequestMapping(value="/date/{date}", method=RequestMethod.GET)
    public WaterConsumption getWaterConsumptionByDate(
            @PathVariable(value="date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        final WaterConsumption byDate = waterConsumptionRepository.findByDate(date);
        if(byDate == null) {
            throw new EntityNotFoundException();
        }

        return byDate;
    }

    @RequestMapping(value="/date/{startDate}/{endDate}", method=RequestMethod.GET)
    public Iterable<WaterConsumption> getWaterConsumptionBetweenDate(
            @PathVariable(value="startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @PathVariable(value="endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        return waterConsumptionRepository.findAllByDateBetween(startDate, endDate);
    }

    protected WaterConsumptionRepository getRepository() {
        return this.waterConsumptionRepository;
    }
}
