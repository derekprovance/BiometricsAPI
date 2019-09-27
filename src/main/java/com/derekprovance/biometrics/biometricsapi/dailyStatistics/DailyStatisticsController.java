package com.derekprovance.biometrics.biometricsapi.dailyStatistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class DailyStatisticsController {
    @Autowired
    private DailyStatisticsRepository dailyStatisticsRepository;
    private java.util.Date dt = new java.util.Date();
    private java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @RequestMapping(name="/DailyStatisticsEntries/", method=GET)
    public Iterable<DailyStatistics> getDailyStatisticsEntries() {
        return dailyStatisticsRepository.findAll();
    }

    @GetMapping("/DailyStatisticsEntries/{id}")
    public DailyStatistics getSingleDailyStatisticsEntry(@PathVariable Integer id) {
        return dailyStatisticsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id.toString()));
    }

    @PostMapping("/DailyStatisticsEntries")
    public DailyStatistics newDailyStatisticsEntry(@RequestBody DailyStatistics newEntry) {
        newEntry.setEntryDate(sdf.format(dt));

        return dailyStatisticsRepository.save(newEntry);
    }
}
