package com.derekprovance.biometrics.biometricsapi.api.dataTracking.dailyStatistics;

import com.derekprovance.biometrics.biometricsapi.api.AbstractApiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;

@RestController
@RequestMapping("/daily-statistics")
public class DailyStatisticsController extends AbstractApiController {
    private final DailyStatisticsRepository dailyStatisticsRepository;

    @Autowired
    public DailyStatisticsController(DailyStatisticsRepository dailyStatisticsRepository) {
        this.dailyStatisticsRepository = dailyStatisticsRepository;
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getSingleDailyStatisticsEntry(@PathVariable Integer id) {
        final DailyStatistics dailyStatistics = dailyStatisticsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not Found: " + id.toString()));

        return ResponseEntity.ok().body(gson.toJson(dailyStatistics));
    }

    @PostMapping("")
    public DailyStatistics newDailyStatisticsEntry(@RequestBody DailyStatistics newEntry) {
        newEntry.setEntryDate(LocalDate.now());

        return dailyStatisticsRepository.save(newEntry);
    }

    @RequestMapping(value="/date/{date}", method=RequestMethod.GET)
    public DailyStatistics getDailyStatisticsByDate(
            @PathVariable(value="date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        final DailyStatistics byEntryDate = dailyStatisticsRepository.findByEntryDate(date);
        if(byEntryDate == null) {
            throw new EntityNotFoundException();
        }

        return byEntryDate;
    }

    @RequestMapping(value="/date/{startDate}/{endDate}", method=RequestMethod.GET)
    public Iterable<DailyStatistics> getDailyStatisticsBetweenDate(
            @PathVariable(value="startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @PathVariable(value="endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        return dailyStatisticsRepository.findAllByEntryDateBetween(startDate, endDate);
    }
}
