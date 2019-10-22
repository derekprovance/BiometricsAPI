package com.derekprovance.biometrics.biometricsapi.api.dailyLog;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface DailyLogRepository extends CrudRepository<DailyLog, Integer> {
    DailyLog findByDate(LocalDate date);
    List<DailyLog> findByDateBetween(LocalDateTime start, LocalDateTime end);
}