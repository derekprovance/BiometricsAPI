package com.derekprovance.biometrics.biometricsapi.api.dataTracking.movementData;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MovementDataRepository extends CrudRepository<MovementData, Integer> {
    List<MovementData> findAllByEventTimeBetween(LocalDateTime start, LocalDateTime end);
}
