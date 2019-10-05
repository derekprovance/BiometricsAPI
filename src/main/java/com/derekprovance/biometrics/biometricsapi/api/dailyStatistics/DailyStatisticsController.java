package com.derekprovance.biometrics.biometricsapi.api.dailyStatistics;

import com.derekprovance.biometrics.biometricsapi.api.AbstractApiController;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Date;

@RestController
public class DailyStatisticsController extends AbstractApiController {
    private DailyStatisticsRepository dailyStatisticsRepository;

    @Autowired
    public DailyStatisticsController(DailyStatisticsRepository dailyStatisticsRepository) {
        this.dailyStatisticsRepository = dailyStatisticsRepository;
    }

    @RequestMapping(value="/daily-statistics-entries/{startDate}", method=RequestMethod.GET)
    public Iterable<DailyStatistics> getDailyStatisticsByDate(
            @PathVariable(value="startDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date date
    ) {
        return dailyStatisticsRepository.findAllByEntryDateBetween(getBeginningOfDay(date), getEndOfDay(date));
    }

    @RequestMapping(value="/daily-statistics-entries/{startDate}/{endDate}", method=RequestMethod.GET)
    public Iterable<DailyStatistics> getDailyStatisticsBetweenDate(
            @PathVariable(value="startDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
            @PathVariable(value="endDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate
    ) {
        return dailyStatisticsRepository.findAllByEntryDateBetween(getBeginningOfDay(startDate), getEndOfDay(endDate));
    }

    @RequestMapping(value="/daily-statistics-entries/{id}", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getSingleDailyStatisticsEntry(@PathVariable Integer id) {
        try {
            final DailyStatistics dailyStatistics = dailyStatisticsRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Not Found: " + id.toString()));

            return ResponseEntity.ok().body(gson.toJson(dailyStatistics));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/daily-statistics-entries")
    public DailyStatistics newDailyStatisticsEntry(@RequestBody DailyStatistics newEntry) {
        newEntry.setEntryDate(new Date());

        return dailyStatisticsRepository.save(newEntry);
    }
}
