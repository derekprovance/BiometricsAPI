package com.derekprovance.biometrics.biometricsapi.database.repository;

import com.derekprovance.biometrics.biometricsapi.api.physio.mealLog.MealBlock;
import com.derekprovance.biometrics.biometricsapi.database.entity.MealLog;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface MealLogRepositoryGeneric extends GenericCrudDateRepository<MealLog, Integer> {
    MealLog findByLogId(Long logId);

    @Query(value = "SELECT MAX(meal_type_id) FROM biometrics.meal_log WHERE date = ?1", nativeQuery = true)
    MealBlock findLatestMeal(LocalDate date);
}