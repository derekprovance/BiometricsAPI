package com.derekprovance.biometrics.biometricsapi.api.physio.meals;

import com.derekprovance.biometrics.biometricsapi.api.singleEntity.AbstractSingleEntityApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/meal")
public class MealController extends AbstractSingleEntityApi {
    private final MealRepository mealRepository;

    @Autowired
    public MealController(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    @PostMapping("/")
    public MealEntry newMealEntry(@RequestBody MealEntry newEntry) {
        return mealRepository.save(newEntry);
    }

    protected MealRepository getRepository() {
        return this.mealRepository;
    }
}
