package com.derekprovance.biometrics.biometricsapi.api.meals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
public class MealController {
    @Autowired
    private MealRepository mealRepository;
    private java.util.Date dt = new java.util.Date();
    private java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @GetMapping("/meal/")
    public Iterable<MealEntry> getMealEntries() {
        return mealRepository.findAll();
    }

    @GetMapping("/meal/{id}")
    public MealEntry getSingleMealEntry(@PathVariable Integer id) {
        return mealRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id.toString()));
    }

    @PostMapping("/meal")
    public MealEntry newMealEntry(@RequestBody MealEntry newEntry) {
        return mealRepository.save(newEntry);
    }
}
