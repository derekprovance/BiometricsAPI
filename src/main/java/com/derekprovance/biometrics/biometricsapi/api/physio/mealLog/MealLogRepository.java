package com.derekprovance.biometrics.biometricsapi.api.physio.mealLog;

import com.derekprovance.biometrics.biometricsapi.api.genericEntities.single.CrudSingleRepository;

import java.time.LocalDate;
import java.util.List;

public interface MealLogRepository extends CrudSingleRepository<MealLog, Integer> {
    MealLog findByLogId(Long logId);
    List<MealLog> findAllByDate(LocalDate date);
}