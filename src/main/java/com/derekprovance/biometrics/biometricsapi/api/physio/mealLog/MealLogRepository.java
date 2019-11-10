package com.derekprovance.biometrics.biometricsapi.api.physio.mealLog;

import com.derekprovance.biometrics.biometricsapi.api.genericEntities.date.CrudDateRepository;

public interface MealLogRepository extends CrudDateRepository<MealLog, Integer> {
    MealLog findByLogId(Long logId);
}