package com.derekprovance.biometrics.biometricsapi.api.dailyStatistics;

import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface DailyStatisticsRepository extends CrudRepository<DailyStatistics, Integer> {
    DailyStatistics findByEntryDate(Date date);
    List<DailyStatistics> findAllByEntryDateBetween(Date start, Date end);
}