package com.derekprovance.biometrics.biometricsapi.api.meals;

import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface MealRepository extends CrudRepository<MealEntry, Integer> {
    MealEntry findByLogId(Long logId);
    List<MealEntry> findAllByDateBetween(Date start, Date end);
}