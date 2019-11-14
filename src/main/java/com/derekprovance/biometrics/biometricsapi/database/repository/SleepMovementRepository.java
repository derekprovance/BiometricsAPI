package com.derekprovance.biometrics.biometricsapi.database.repository;

import com.derekprovance.biometrics.biometricsapi.database.entity.SleepMovement;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SleepMovementRepository extends CrudRepository<SleepMovement, Integer> {
    List<SleepMovement> findAllByStartBetween(LocalDateTime start, LocalDateTime end);
}
