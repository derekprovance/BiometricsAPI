package com.derekprovance.biometrics.biometricsapi.api.bloodSugar;

import com.derekprovance.biometrics.biometricsapi.api.AbstractApiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RestController
@RequestMapping("/blood-sugar")
public class BloodSugarController extends AbstractApiController {
    private BloodSugarRepository bloodSugarRepository;

    @Autowired
    public BloodSugarController(BloodSugarRepository bloodSugarRepository) {
        this.bloodSugarRepository = bloodSugarRepository;
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getSingleBloodSugarEntry(@PathVariable Integer id) {
        final BloodSugar bloodSugar = bloodSugarRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not Found: " + id.toString()));

        return ResponseEntity.ok().body(gson.toJson(bloodSugar));
    }

    @PostMapping("")
    public BloodSugar newBloodSugarEntry(@RequestBody BloodSugar newEntry) {
        if(newEntry.getDatetime() == null) {
            newEntry.setDatetime(LocalDateTime.now());
        }
        return bloodSugarRepository.save(newEntry);
    }

    @RequestMapping(value="/date/{date}", method=RequestMethod.GET)
    public Iterable<BloodSugar> getBloodSugarByDate(
            @PathVariable(value="date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return bloodSugarRepository.findByDatetimeBetween(date.atStartOfDay(), date.atTime(LocalTime.MAX));
    }

    @RequestMapping(value="/date/{startDate}/{endDate}", method=RequestMethod.GET)
    public Iterable<BloodSugar> getBloodSugarBetweenDate(
            @PathVariable(value="startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @PathVariable(value="endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        return bloodSugarRepository.findByDatetimeBetween(startDate.atStartOfDay(), endDate.atTime(LocalTime.MAX));
    }
}
