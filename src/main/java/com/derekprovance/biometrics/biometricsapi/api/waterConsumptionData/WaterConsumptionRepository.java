package com.derekprovance.biometrics.biometricsapi.api.waterConsumptionData;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface WaterConsumptionRepository extends CrudRepository<WaterConsumption, Integer> {
    WaterConsumption findByDate(LocalDate date);
    List<WaterConsumption> findAllByDateBetween(LocalDateTime start, LocalDateTime end);
}
