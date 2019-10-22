package com.derekprovance.biometrics.biometricsapi.api.meals;

import com.derekprovance.biometrics.biometricsapi.api.AbstractApiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;

@RestController
@RequestMapping("/meal")
public class MealController extends AbstractApiController {
    private MealRepository mealRepository;

    @Autowired
    public MealController(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getSingleMealEntryEntry(@PathVariable Integer id) {
        try {
            final MealEntry mealEntry = mealRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Not Found: " + id.toString()));

            return ResponseEntity.ok().body(gson.toJson(mealEntry));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public MealEntry newMealEntry(@RequestBody MealEntry newEntry) {
        return mealRepository.save(newEntry);
    }

    @RequestMapping(value="/date/{date}", method=RequestMethod.GET)
    public Iterable<MealEntry> getMealEntryByDate(
            @PathVariable(value="date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return mealRepository.findAllByDateBetween(date, date);
    }

    @RequestMapping(value="/date/{startDate}/{endDate}", method=RequestMethod.GET)
    public Iterable<MealEntry> getMealEntryBetweenDates(
            @PathVariable(value="startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @PathVariable(value="endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        return mealRepository.findAllByDateBetween(startDate, endDate);
    }
}
