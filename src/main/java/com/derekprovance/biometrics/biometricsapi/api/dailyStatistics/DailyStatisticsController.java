package com.derekprovance.biometrics.biometricsapi.api.dailyStatistics;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
public class DailyStatisticsController {
    private DailyStatisticsRepository dailyStatisticsRepository;
    private final java.util.Date dt = new java.util.Date();
    private final java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final Gson gson = new Gson();

    @GetMapping("/daily-statistics-entries/")
    public Iterable<DailyStatistics> getDailyStatisticsEntries() {
        return dailyStatisticsRepository.findAll();
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
        newEntry.setEntryDate(sdf.format(dt));

        return dailyStatisticsRepository.save(newEntry);
    }
}
