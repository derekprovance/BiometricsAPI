package com.derekprovance.biometrics.biometricsapi.api.physio.waterConsumption;

import com.derekprovance.biometrics.biometricsapi.api.generic.date.AbstractDateSingleEntityApi;
import com.derekprovance.biometrics.biometricsapi.database.entity.WaterConsumption;
import com.derekprovance.biometrics.biometricsapi.database.repository.WaterConsumptionRepositoryGeneric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/water-consumption")
public class WaterConsumptionController extends AbstractDateSingleEntityApi {
    private final WaterConsumptionRepositoryGeneric waterConsumptionRepository;

    @Autowired
    public WaterConsumptionController(
            WaterConsumptionRepositoryGeneric waterConsumptionRepository
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

    protected WaterConsumptionRepositoryGeneric getRepository() {
        return this.waterConsumptionRepository;
    }
}
