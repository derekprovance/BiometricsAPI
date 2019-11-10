package com.derekprovance.biometrics.biometricsapi.api.physio.waterConsumption;

import com.derekprovance.biometrics.biometricsapi.api.genericEntities.single.AbstractDateSingleEntityApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/water-consumption")
public class WaterConsumptionController extends AbstractDateSingleEntityApi {
    private final WaterConsumptionRepository waterConsumptionRepository;

    @Autowired
    public WaterConsumptionController(
            WaterConsumptionRepository waterConsumptionRepository
    ) {
        this.waterConsumptionRepository = waterConsumptionRepository;
    }

    @PostMapping("")
    public WaterConsumption newWaterConsumptionDataEntry(@RequestBody WaterConsumption newEntry) {
        if(newEntry.getDate() == null) {
            newEntry.setDate(LocalDate.now());
        }

        final WaterConsumption waterConsumption = (WaterConsumption) waterConsumptionRepository.findByDate(newEntry.getDate());
        if(waterConsumption != null) {
            newEntry.setId(waterConsumption.getId());
        }

        return waterConsumptionRepository.save(newEntry);
    }

    protected WaterConsumptionRepository getRepository() {
        return this.waterConsumptionRepository;
    }
}
