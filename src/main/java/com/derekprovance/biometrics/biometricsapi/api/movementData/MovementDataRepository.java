package com.derekprovance.biometrics.biometricsapi.api.movementData;

import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface MovementDataRepository extends CrudRepository<MovementData, Integer> {
    List<MovementData> findAllByEventTimeBetween(Date start, Date end);
}
