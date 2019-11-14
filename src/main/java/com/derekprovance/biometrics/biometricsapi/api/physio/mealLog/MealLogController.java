package com.derekprovance.biometrics.biometricsapi.api.physio.mealLog;

import com.derekprovance.biometrics.biometricsapi.api.generic.date.AbstractDateMultipleEntityApi;
import com.derekprovance.biometrics.biometricsapi.database.entity.MealLog;
import com.derekprovance.biometrics.biometricsapi.database.repository.MealLogRepositoryGeneric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Objects;

@RestController
@RequestMapping("/meal")
public class MealLogController extends AbstractDateMultipleEntityApi {
    private final MealLogRepositoryGeneric mealLogRepository;

    @Autowired
    public MealLogController(MealLogRepositoryGeneric mealLogRepository) {
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

    protected MealLogRepositoryGeneric getRepository() {
        return this.mealLogRepository;
    }
}
