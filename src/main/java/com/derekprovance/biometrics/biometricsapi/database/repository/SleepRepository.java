package com.derekprovance.biometrics.biometricsapi.database.repository;

import com.derekprovance.biometrics.biometricsapi.database.entity.Sleep;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SleepRepository extends CrudRepository<Sleep, Integer> {
    Sleep findFirstBySleepStartBetween(LocalDateTime start, LocalDateTime end);
    List<Sleep> findAllBySleepStartBetween(LocalDateTime start, LocalDateTime end);
}
