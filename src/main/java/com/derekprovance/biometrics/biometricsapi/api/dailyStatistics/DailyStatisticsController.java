package com.derekprovance.biometrics.biometricsapi.api.dailyStatistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
public class DailyStatisticsController {
    private DailyStatisticsRepository dailyStatisticsRepository;
    private final java.util.Date dt = new java.util.Date();
    private final java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    public DailyStatisticsController(DailyStatisticsRepository dailyStatisticsRepository) {
        this.dailyStatisticsRepository = dailyStatisticsRepository;
    }

    @GetMapping("/daily-statistics-entries/")
    public Iterable<DailyStatistics> getDailyStatisticsEntries() {
        return dailyStatisticsRepository.findAll();
    }

    @GetMapping("/daily-statistics-entries/{id}")
    public DailyStatistics getSingleDailyStatisticsEntry(@PathVariable Integer id) {
        return dailyStatisticsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id.toString()));
    }

    @PostMapping("/daily-statistics-entries")
    public DailyStatistics newDailyStatisticsEntry(@RequestBody DailyStatistics newEntry) {
        newEntry.setEntryDate(sdf.format(dt));

        return dailyStatisticsRepository.save(newEntry);
    }
}
