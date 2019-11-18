package com.derekprovance.biometrics.biometricsapi.database.repository;

import com.derekprovance.biometrics.biometricsapi.database.entity.SleepLevel;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SleepLevelRepository extends CrudRepository<SleepLevel, Integer> {
    List<SleepLevel> findAllByStartBetween(LocalDateTime start, LocalDateTime end);
}
