package com.derekprovance.biometrics.biometricsapi.api.physio.waterConsumption;

import com.derekprovance.biometrics.biometricsapi.api.AbstractBioDateSingleEntityApi;
import com.derekprovance.biometrics.biometricsapi.database.entity.WaterConsumption;
import com.derekprovance.biometrics.biometricsapi.database.repository.WaterConsumptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/water-consumption")
public class WaterConsumptionController extends AbstractBioDateSingleEntityApi {
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

        final WaterConsumption waterConsumption = (WaterConsumption) waterConsumptionRepository.findFirstByDate(newEntry.getDate());
        if(waterConsumption != null) {
            newEntry.setId(waterConsumption.getId());
        }

        return waterConsumptionRepository.save(newEntry);
    }

    protected WaterConsumptionRepository getRepository() {
        return this.waterConsumptionRepository;
    }
}
