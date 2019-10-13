package com.derekprovance.biometrics.biometricsapi.api.meals;

import com.derekprovance.biometrics.biometricsapi.api.AbstractApiController;
import com.derekprovance.biometrics.biometricsapi.services.fitbit.FoodLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;

@RestController
public class MealController extends AbstractApiController {
    private MealRepository mealRepository;
    private FoodLogService foodLogService;

    @Autowired
    public MealController(MealRepository mealRepository, FoodLogService foodLogService) {
        this.mealRepository = mealRepository;
        this.foodLogService = foodLogService;
    }

    @RequestMapping(value="/meal/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getSingleMealEntryEntry(@PathVariable Integer id) {
        try {
            final MealEntry mealEntry = mealRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Not Found: " + id.toString()));

            return ResponseEntity.ok().body(gson.toJson(mealEntry));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/meal/fitbit/{date}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> pullMealsFromFitbitByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        Integer count = foodLogService.syncWithDatabase(date);

        return ResponseEntity.status(HttpStatus.OK).body(String.format("{\"status\": \"%s\", \"message\": \"Processed %d entities for date %s\"}", HttpStatus.OK, count, date.toString()));
    }

    @PostMapping("/meal")
    public MealEntry newMealEntry(@RequestBody MealEntry newEntry) {
        return mealRepository.save(newEntry);
    }

    @RequestMapping(value="/meal/date/{startDate}", method=RequestMethod.GET)
    public Iterable<MealEntry> getMealEntryByDate(
            @PathVariable(value="startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return mealRepository.findAllByDateBetween(date.atStartOfDay(), date.atTime(LocalTime.MAX));
    }

    @RequestMapping(value="/meal/date/{startDate}/{endDate}", method=RequestMethod.GET)
    public Iterable<MealEntry> getMealEntryBetweenDates(
            @PathVariable(value="startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @PathVariable(value="endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        return mealRepository.findAllByDateBetween(startDate.atStartOfDay(), endDate.atTime(LocalTime.MAX));
    }
}
