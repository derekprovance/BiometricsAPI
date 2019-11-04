package com.derekprovance.biometrics.biometricsapi.api.dataTracking.hrData;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface HrDataRepository extends CrudRepository<HrData, Integer> {
    List<HrData> findAllByEventTimeBetween(LocalDateTime start, LocalDateTime end);
}
