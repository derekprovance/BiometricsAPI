package com.derekprovance.biometrics.biometricsapi.api.dataTracking.dailyLog;

import com.derekprovance.biometrics.biometricsapi.api.AbstractApiController;
import com.derekprovance.biometrics.biometricsapi.api.dataTracking.AbstractDataTrackingApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;

@RestController
@RequestMapping("/daily-log")
public class DailyLogController extends AbstractDataTrackingApi {
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
        return dailyLogRepository.save(newEntry);
    }

    @RequestMapping(value="/date/{date}", method=RequestMethod.GET)
    public ResponseEntity<String> getDailyLogByDate(
            @PathVariable(value="date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        final DailyLog dailyLog = dailyLogRepository.findByDate(date);

        if(dailyLog == null) {
            throw new EntityNotFoundException("Not Found: " + date);
        }

        return ResponseEntity.ok().body(gson.toJson(dailyLog));
    }

    @RequestMapping(value="/date/{startDate}/{endDate}", method=RequestMethod.GET)
    public Iterable<DailyLog> getDailyLogBetweenDate(
            @PathVariable(value="startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @PathVariable(value="endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        return dailyLogRepository.findByDateBetween(startDate.atStartOfDay(), endDate.atTime(LocalTime.MAX));
    }

    protected DailyLogRepository getRepository() {
        return this.dailyLogRepository;
    }
}
