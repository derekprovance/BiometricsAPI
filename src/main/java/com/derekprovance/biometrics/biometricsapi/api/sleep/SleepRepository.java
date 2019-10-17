package com.derekprovance.biometrics.biometricsapi.api.sleep;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SleepRepository extends CrudRepository<Sleep, Integer> {
    Sleep findBySleepStartBetween(LocalDateTime start, LocalDateTime end);
    List<Sleep> findAllBySleepStartBetween(LocalDateTime start, LocalDateTime end);

}
