package com.derekprovance.biometrics.biometricsapi.api.bloodSugar;

import com.derekprovance.biometrics.biometricsapi.api.AbstractApiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Date;

@RestController
public class BloodSugarController extends AbstractApiController {
    private BloodSugarRepository bloodSugarRepository;

    @Autowired
    public BloodSugarController(BloodSugarRepository bloodSugarRepository) {
        this.bloodSugarRepository = bloodSugarRepository;
    }

    @RequestMapping(value="/blood-sugar-entries/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getSingleBloodSugarEntry(@PathVariable Integer id) {
        try {
            final BloodSugar bloodSugar = bloodSugarRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Not Found: " + id.toString()));

            return ResponseEntity.ok().body(gson.toJson(bloodSugar));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/blood-sugar-entries")
    public BloodSugar newBloodSugarEntry(@RequestBody BloodSugar newEntry) {
        newEntry.setDatetime(new Date());

        return bloodSugarRepository.save(newEntry);
    }

    @RequestMapping(value="/blood-sugar-entries/date/{startDate}", method=RequestMethod.GET)
    public Iterable<BloodSugar> getBloodSugarByDate(
            @PathVariable(value="startDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date date
    ) {
        return bloodSugarRepository.findByDatetimeBetween(getBeginningOfDay(date), getEndOfDay(date));
    }

    @RequestMapping(value="/blood-sugar-entries/date/{startDate}/{endDate}", method=RequestMethod.GET)
    public Iterable<BloodSugar> getBloodSugarBetweenDate(
            @PathVariable(value="startDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
            @PathVariable(value="endDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate
    ) {
        return bloodSugarRepository.findByDatetimeBetween(getBeginningOfDay(startDate), getEndOfDay(endDate));
    }
}
