package com.derekprovance.biometrics.biometricsapi.api.waterConsumptionData;

import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface WaterConsumptionRepository extends CrudRepository<WaterConsumption, Integer> {
    WaterConsumption findByDate(Date date);
}
