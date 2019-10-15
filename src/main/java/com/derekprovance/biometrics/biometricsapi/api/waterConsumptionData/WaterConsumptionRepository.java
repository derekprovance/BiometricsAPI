package com.derekprovance.biometrics.biometricsapi.api.waterConsumptionData;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface WaterConsumptionRepository extends CrudRepository<WaterConsumption, Integer> {
    WaterConsumption findByDate(LocalDate date);
    List<WaterConsumption> findAllByDateBetween(LocalDate start, LocalDate end);
}
