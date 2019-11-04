package com.derekprovance.biometrics.biometricsapi.api.dataTracking.movementData;

import com.derekprovance.biometrics.biometricsapi.api.AbstractApiController;
import com.derekprovance.biometrics.biometricsapi.api.dataTracking.AbstractDataTrackingApi;
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
@RequestMapping("/movement-data")
public class MovementDataController extends AbstractDataTrackingApi {
    private final MovementDataRepository movementDataRepository;

    @Autowired
    public MovementDataController(MovementDataRepository movementDataRepository) {
        this.movementDataRepository = movementDataRepository;
    }

    @PostMapping("")
    public MovementData newMovementDataEntry(@RequestBody MovementData newEntry) {
        newEntry.setEventTime(LocalDateTime.now());

        return movementDataRepository.save(newEntry);
    }

    @RequestMapping(value="/date/{date}", method=RequestMethod.GET)
    public Iterable<MovementData> getMovementDataByDate(
            @PathVariable(value="date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return movementDataRepository.findAllByEventTimeBetween(date.atStartOfDay(), date.atTime(LocalTime.MAX));
    }

    @RequestMapping(value="/date/{startDate}/{endDate}", method=RequestMethod.GET)
    public Iterable<MovementData> getMovementDataBetweenDate(
            @PathVariable(value="startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @PathVariable(value="endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        return movementDataRepository.findAllByEventTimeBetween(startDate.atStartOfDay(), endDate.atTime(LocalTime.MAX));
    }

    protected MovementDataRepository getRepository() {
        return this.movementDataRepository;
    }
}
