package com.derekprovance.biometrics.biometricsapi.api.physio.mealLog;

import com.derekprovance.biometrics.biometricsapi.api.genericEntities.single.CrudSingleRepository;

public interface MealLogRepository extends CrudSingleRepository<MealLog, Integer> {
    MealLog findByLogId(Long logId);
}