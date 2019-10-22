package com.derekprovance.biometrics.biometricsapi.api.dailyLog;

import com.derekprovance.biometrics.biometricsapi.api.AbstractApiController;
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
public class DailyLogController extends AbstractApiController {
    private DailyLogRepository dailyLogRepository;

    @Autowired
    public DailyLogController(DailyLogRepository dailyLogRepository) {
        this.dailyLogRepository = dailyLogRepository;
    }

    @RequestMapping(value="/{id}", method= RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getSingleDailyLogEntry(@PathVariable Integer id) {
        try {
            final DailyLog DailyLog = dailyLogRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Not Found: " + id.toString()));

            return ResponseEntity.ok().body(gson.toJson(DailyLog));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public DailyLog newDailyLogEntry(@RequestBody DailyLog newEntry) {
        if(newEntry.getDate() == null) {
            newEntry.setDate(LocalDate.now());
        }
        return dailyLogRepository.save(newEntry);
    }

    @RequestMapping(value="/date/{date}", method=RequestMethod.GET)
    public Iterable<DailyLog> getDailyLogByDate(
            @PathVariable(value="date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return dailyLogRepository.findByDateBetween(date.atStartOfDay(), date.atTime(LocalTime.MAX));
    }

    @RequestMapping(value="/date/{startDate}/{endDate}", method=RequestMethod.GET)
    public Iterable<DailyLog> getDailyLogBetweenDate(
            @PathVariable(value="startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @PathVariable(value="endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        return dailyLogRepository.findByDateBetween(startDate.atStartOfDay(), endDate.atTime(LocalTime.MAX));
    }
}
