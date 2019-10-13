package com.derekprovance.biometrics.biometricsapi.api.bloodSugar;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BloodSugarRepository extends CrudRepository<BloodSugar, Integer> {
    List<BloodSugar> findByDatetimeBetween(LocalDateTime start, LocalDateTime end);
}