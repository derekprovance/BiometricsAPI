package com.derekprovance.biometrics.biometricsapi.api.physio.waterConsumption;

import com.derekprovance.biometrics.biometricsapi.api.singleEntity.AbstractSingleEntityApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/water-consumption")
public class WaterConsumptionController extends AbstractSingleEntityApi {
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

    protected WaterConsumptionRepository getRepository() {
        return this.waterConsumptionRepository;
    }
}
