package com.derekprovance.biometrics.biometricsapi.api.meals;

import com.derekprovance.biometrics.biometricsapi.api.AbstractApiController;
import com.derekprovance.biometrics.biometricsapi.services.fitbit.FoodLogService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.text.ParseException;
import java.util.Date;

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

    @RequestMapping(value = "/meal/fitbit/{dateStr}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> pullMealsFromFitbitByDate(@PathVariable String dateStr) {
        try {
            Date date = simpleDateFormat.parse(dateStr);
            Integer count = foodLogService.syncWithDatabase(date);

            return ResponseEntity.status(HttpStatus.OK).body(String.format("{\"status\": \"%s\", \"message\": \"Processed %d entities for date %s\"}", HttpStatus.OK, count, dateStr));
        } catch (ParseException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(String.format("{\"status\": \"%s\", \"message\": \"Date format must be YYYY-MM-DD\"}", HttpStatus.BAD_REQUEST));
        }
    }

    @PostMapping("/meal")
    public MealEntry newMealEntry(@RequestBody MealEntry newEntry) {
        return mealRepository.save(newEntry);
    }

    @RequestMapping(value="/meal/date/{startDate}", method=RequestMethod.GET)
    public Iterable<MealEntry> getMealEntryByDate(
            @PathVariable(value="startDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date date
    ) {
        return mealRepository.findAllByDateBetween(getBeginningOfDay(date), getEndOfDay(date));
    }

    @RequestMapping(value="/meal/date/{startDate}/{endDate}", method=RequestMethod.GET)
    public Iterable<MealEntry> getMealEntryBetweenDates(
            @PathVariable(value="startDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
            @PathVariable(value="endDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate
    ) {
        return mealRepository.findAllByDateBetween(getBeginningOfDay(startDate), getEndOfDay(endDate));
    }
}
