package com.derekprovance.biometrics.biometricsapi.api.dataTracking.meals;

import com.derekprovance.biometrics.biometricsapi.api.singleEntity.CrudSingleRepository;

public interface MealRepository extends CrudSingleRepository<MealEntry, Integer> {
    MealEntry findByLogId(Long logId);
}