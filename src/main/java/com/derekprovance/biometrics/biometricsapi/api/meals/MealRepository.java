package com.derekprovance.biometrics.biometricsapi.api.meals;

import org.springframework.data.repository.CrudRepository;

public interface MealRepository extends CrudRepository<MealEntry, Integer> {
    MealEntry findByLogId(Long logId);
}