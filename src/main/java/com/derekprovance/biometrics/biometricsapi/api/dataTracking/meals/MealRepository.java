package com.derekprovance.biometrics.biometricsapi.api.dataTracking.meals;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface MealRepository extends CrudRepository<MealEntry, Integer> {
    MealEntry findByLogId(Long logId);
    List<MealEntry> findAllByDateBetween(LocalDate start, LocalDate end);
}