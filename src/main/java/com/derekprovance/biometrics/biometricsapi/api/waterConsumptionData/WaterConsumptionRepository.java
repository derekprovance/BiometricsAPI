package com.derekprovance.biometrics.biometricsapi.api.waterConsumptionData;

import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface WaterConsumptionRepository extends CrudRepository<WaterConsumption, Integer> {
    WaterConsumption findByDate(Date date);
    List<WaterConsumption> findAllByDateBetween(Date start, Date end);
}
