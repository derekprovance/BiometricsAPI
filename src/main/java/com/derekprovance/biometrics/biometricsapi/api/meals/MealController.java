package com.derekprovance.biometrics.biometricsapi.api.meals;

import com.derekprovance.biometrics.biometricsapi.services.fitbit.FoodLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class MealController {
    private MealRepository mealRepository;
    private FoodLogService foodLogService;

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    public MealController(MealRepository mealRepository, FoodLogService foodLogService) {
        this.mealRepository = mealRepository;
        this.foodLogService = foodLogService;
    }

    @GetMapping("/meal/")
    public Iterable<MealEntry> getMealEntries() {
        return mealRepository.findAll();
    }

    @GetMapping("/meal/{id}")
    public MealEntry getSingleMealEntry(@PathVariable Integer id) {
        return mealRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id.toString()));
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
}
