package com.derekprovance.biometrics.biometricsapi.api.movementData;

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
public class MovementDataController extends AbstractApiController {
    private MovementDataRepository movementDataRepository;

    @Autowired
    public MovementDataController(MovementDataRepository movementDataRepository) {
        this.movementDataRepository = movementDataRepository;
    }

    @RequestMapping(value="/movement-data/{id}", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getSingleBloodSugarEntry(@PathVariable Integer id) {
        try {
            final MovementData movementData = movementDataRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Not Found: " + id.toString()));

            return ResponseEntity.ok().body(gson.toJson(movementData));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/movement-data")
    public MovementData newMovementDataEntry(@RequestBody MovementData newEntry) {
        newEntry.setEventTime(LocalDateTime.now());

        return movementDataRepository.save(newEntry);
    }

    @RequestMapping(value="/movement-data/date/{startDate}", method=RequestMethod.GET)
    public Iterable<MovementData> getMovementDateByDate(
            @PathVariable(value="startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return movementDataRepository.findAllByEventTimeBetween(date.atStartOfDay(), date.atTime(LocalTime.MAX));
    }

    @RequestMapping(value="/movement-data/date/{startDate}/{endDate}", method=RequestMethod.GET)
    public Iterable<MovementData> getMovementDataBetweenDate(
            @PathVariable(value="startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @PathVariable(value="endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        return movementDataRepository.findAllByEventTimeBetween(startDate.atStartOfDay(), endDate.atTime(LocalTime.MAX));
    }
}
