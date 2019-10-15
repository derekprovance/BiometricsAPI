package com.derekprovance.biometrics.biometricsapi.api.dailyStatistics;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface DailyStatisticsRepository extends CrudRepository<DailyStatistics, Integer> {
    DailyStatistics findByEntryDate(LocalDate date);
    List<DailyStatistics> findAllByEntryDateBetween(LocalDate start, LocalDate end);
}