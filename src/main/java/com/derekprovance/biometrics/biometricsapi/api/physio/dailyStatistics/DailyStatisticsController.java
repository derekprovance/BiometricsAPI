package com.derekprovance.biometrics.biometricsapi.api.physio.dailyStatistics;

import com.derekprovance.biometrics.biometricsapi.api.generic.date.AbstractDateSingleEntityApi;
import com.derekprovance.biometrics.biometricsapi.database.entity.DailyStatistics;
import com.derekprovance.biometrics.biometricsapi.database.repository.DailyStatisticsRepositoryGeneric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Objects;

@RestController
@RequestMapping("/daily-statistics")
public class DailyStatisticsController extends AbstractDateSingleEntityApi {
    private final DailyStatisticsRepositoryGeneric dailyStatisticsRepository;

    @Autowired
    public DailyStatisticsController(DailyStatisticsRepositoryGeneric dailyStatisticsRepository) {
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
    protected DailyStatisticsRepositoryGeneric getRepository() {
        return this.dailyStatisticsRepository;
    }
}
