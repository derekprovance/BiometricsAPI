package com.derekprovance.biometrics.biometricsapi.api.psych.dailyLog;

import com.derekprovance.biometrics.biometricsapi.api.genericEntities.single.AbstractSingleEntityApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Objects;

@RestController
@RequestMapping("/daily-log")
public class DailyLogController extends AbstractSingleEntityApi {
    private final DailyLogRepository dailyLogRepository;

    @Autowired
    public DailyLogController(DailyLogRepository dailyLogRepository) {
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

    protected DailyLogRepository getRepository() {
        return this.dailyLogRepository;
    }
}
