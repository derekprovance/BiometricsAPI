package com.derekprovance.biometrics.biometricsapi.api.physio.meals;

import com.derekprovance.biometrics.biometricsapi.api.genericEntities.single.AbstractSingleEntityApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Objects;

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
        if(newEntry.getDate() == null) {
            newEntry.setDate(LocalDate.now());
        }

        final MealEntry existingDateEntry = (MealEntry) mealRepository.findByDate(newEntry.getDate());
        if(existingDateEntry != null) {
            newEntry.setId(existingDateEntry.getId());
        }
        return mealRepository.save(Objects.requireNonNullElse(existingDateEntry, newEntry));
    }

    protected MealRepository getRepository() {
        return this.mealRepository;
    }
}
