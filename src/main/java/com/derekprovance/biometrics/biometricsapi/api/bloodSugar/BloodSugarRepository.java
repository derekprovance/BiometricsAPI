package com.derekprovance.biometrics.biometricsapi.api.bloodSugar;

import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface BloodSugarRepository extends CrudRepository<BloodSugar, Integer> {
    List<BloodSugar> findByDatetimeBetween(Date start, Date end);
}