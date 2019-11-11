package com.derekprovance.biometrics.biometricsapi.api.physio.mealLog;

import com.derekprovance.biometrics.biometricsapi.api.genericEntities.date.CrudDateRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface MealLogRepository extends CrudDateRepository<MealLog, Integer> {
    MealLog findByLogId(Long logId);

    @Query(value = "SELECT MAX(meal_type_id) FROM biometrics.meal_log WHERE date = ?1", nativeQuery = true)
    MealBlock findLatestMeal(LocalDate date);
}