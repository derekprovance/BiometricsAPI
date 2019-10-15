package com.derekprovance.biometrics.biometricsapi.api.dailyStatistics;

import com.derekprovance.biometrics.biometricsapi.api.AbstractApiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;

@RestController
public class DailyStatisticsController extends AbstractApiController {
    private DailyStatisticsRepository dailyStatisticsRepository;

    @Autowired
    public DailyStatisticsController(DailyStatisticsRepository dailyStatisticsRepository) {
        this.dailyStatisticsRepository = dailyStatisticsRepository;
    }

    @RequestMapping(value="/daily-statistics/{id}", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getSingleDailyStatisticsEntry(@PathVariable Integer id) {
        try {
            final DailyStatistics dailyStatistics = dailyStatisticsRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Not Found: " + id.toString()));

            return ResponseEntity.ok().body(gson.toJson(dailyStatistics));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/daily-statistics")
    public DailyStatistics newDailyStatisticsEntry(@RequestBody DailyStatistics newEntry) {
        newEntry.setEntryDate(LocalDate.now());

        return dailyStatisticsRepository.save(newEntry);
    }

    @RequestMapping(value="/daily-statistics/date/{date}", method=RequestMethod.GET)
    public DailyStatistics getDailyStatisticsByDate(
            @PathVariable(value="date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return dailyStatisticsRepository.findByEntryDate(date);
    }

    @RequestMapping(value="/daily-statistics/date/{startDate}/{endDate}", method=RequestMethod.GET)
    public Iterable<DailyStatistics> getDailyStatisticsBetweenDate(
            @PathVariable(value="startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @PathVariable(value="endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        return dailyStatisticsRepository.findAllByEntryDateBetween(startDate, endDate);
    }
}
