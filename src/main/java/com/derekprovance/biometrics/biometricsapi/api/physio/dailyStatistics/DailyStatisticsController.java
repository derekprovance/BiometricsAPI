package com.derekprovance.biometrics.biometricsapi.api.physio.dailyStatistics;

import com.derekprovance.biometrics.biometricsapi.api.genericEntities.single.AbstractSingleEntityApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/daily-statistics")
public class DailyStatisticsController extends AbstractSingleEntityApi {
    private final DailyStatisticsRepository dailyStatisticsRepository;

    @Autowired
    public DailyStatisticsController(DailyStatisticsRepository dailyStatisticsRepository) {
        this.dailyStatisticsRepository = dailyStatisticsRepository;
    }

    @PostMapping("")
    public DailyStatistics newDailyStatisticsEntry(@RequestBody DailyStatistics newEntry) {
        newEntry.setDate(LocalDate.now());

        return dailyStatisticsRepository.save(newEntry);
    }

    @Override
    protected DailyStatisticsRepository getRepository() {
        return this.dailyStatisticsRepository;
    }
}
