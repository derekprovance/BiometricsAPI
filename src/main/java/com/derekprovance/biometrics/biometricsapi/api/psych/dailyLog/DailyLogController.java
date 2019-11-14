package com.derekprovance.biometrics.biometricsapi.api.psych.dailyLog;

import com.derekprovance.biometrics.biometricsapi.api.generic.date.AbstractDateSingleEntityApi;
import com.derekprovance.biometrics.biometricsapi.database.entity.DailyLog;
import com.derekprovance.biometrics.biometricsapi.database.repository.DailyLogRepositoryGeneric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Objects;

@RestController
@RequestMapping("/daily-log")
public class DailyLogController extends AbstractDateSingleEntityApi {
    private final DailyLogRepositoryGeneric dailyLogRepository;

    @Autowired
    public DailyLogController(DailyLogRepositoryGeneric dailyLogRepository) {
        this.dailyLogRepository = dailyLogRepository;
    }

    @PostMapping("")
    public DailyLog newDailyLogEntry(@RequestBody DailyLog newEntry) {
        if(newEntry.getDate() == null) {
            newEntry.setDate(LocalDate.now());
        }

        final DailyLog existingDateEntry = (DailyLog) dailyLogRepository.findByDate(newEntry.getDate());
        if(existingDateEntry != null) {
            newEntry.setId(existingDateEntry.getId());
        }

        return dailyLogRepository.save(Objects.requireNonNullElse(existingDateEntry, newEntry));
    }

    protected DailyLogRepositoryGeneric getRepository() {
        return this.dailyLogRepository;
    }
}
