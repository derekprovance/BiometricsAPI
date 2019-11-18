package com.derekprovance.biometrics.biometricsapi.api.physio.dailyStatistics;

import com.derekprovance.biometrics.biometricsapi.api.AbstractBioDateSingleEntityApi;
import com.derekprovance.biometrics.biometricsapi.database.entity.DailyStatistics;
import com.derekprovance.biometrics.biometricsapi.database.repository.DailyStatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Objects;

@RestController
@RequestMapping("/daily-statistics")
public class DailyStatisticsController extends AbstractBioDateSingleEntityApi {
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

        final DailyStatistics existingDateEntry = (DailyStatistics) dailyStatisticsRepository.findFirstByDate(newEntry.getDate());
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
