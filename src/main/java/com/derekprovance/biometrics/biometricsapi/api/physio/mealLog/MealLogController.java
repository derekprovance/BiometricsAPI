package com.derekprovance.biometrics.biometricsapi.api.physio.mealLog;

import com.derekprovance.biometrics.biometricsapi.api.AbstractDataTrackingApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/meal")
public class MealLogController extends AbstractDataTrackingApi {
    private final MealLogRepository mealLogRepository;

    @Autowired
    public MealLogController(MealLogRepository mealLogRepository) {
        this.mealLogRepository = mealLogRepository;
    }

    @PostMapping("/")
    public MealLog newMealEntry(@RequestBody MealLog newEntry) {
        if(newEntry.getDate() == null) {
            newEntry.setDate(LocalDate.now());
        }

        final MealLog existingDateEntry = (MealLog) mealLogRepository.findByDate(newEntry.getDate());
        if(existingDateEntry != null) {
            newEntry.setId(existingDateEntry.getId());
        }
        return mealLogRepository.save(Objects.requireNonNullElse(existingDateEntry, newEntry));
    }

    protected MealLogRepository getRepository() {
        return this.mealLogRepository;
    }

    @RequestMapping(value="/date/{date}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getByDate(
            @PathVariable(value="date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        List<MealLog> meals = getRepository().findAllByDate(date);

        if(meals == null || meals.isEmpty()) {
            throw new EntityNotFoundException("Not Found: " + date);
        }

        return ResponseEntity.ok().body(gson.toJson(meals));
    }

    @RequestMapping(value="/date/{startDate}/{endDate}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<?> getBetweenDate(
            @PathVariable(value="startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @PathVariable(value="endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        return getRepository().findByDateBetween(startDate, endDate);
    }
}
