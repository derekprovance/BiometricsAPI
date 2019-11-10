package com.derekprovance.biometrics.biometricsapi.api.physio.dailyStatistics;

import com.derekprovance.biometrics.biometricsapi.api.genericEntities.single.AbstractDateSingleEntityApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Objects;

@RestController
@RequestMapping("/daily-statistics")
public class DailyStatisticsController extends AbstractDateSingleEntityApi {
    private final DailyStatisticsRepository dailyStatisticsRepository;

    @Autowired
    public DailyStatisticsController(DailyStatisticsRepository dailyStatisticsRepository) {
        this.dailyStatisticsRepository = dailyStatisticsRepository;
    }

    @PostMapping("")
    public DailyStatistics newDailyStatisticsEntry(@RequestBody DailyStatistics newEntry) {
        if(newEntry.getDate() == null) {
            newEntry.setDate(LocalDate.now());
        }

        final DailyStatistics existingDateEntry = (DailyStatistics) dailyStatisticsRepository.findByDate(newEntry.getDate());
        if(existingDateEntry != null) {
            newEntry.setId(existingDateEntry.getId());
        }

        return dailyStatisticsRepository.save(Objects.requireNonNullElse(existingDateEntry, newEntry));
    }

    @Override
    protected DailyStatisticsRepository getRepository() {
        return this.dailyStatisticsRepository;
    }
}
