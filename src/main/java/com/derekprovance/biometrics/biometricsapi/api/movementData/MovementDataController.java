package com.derekprovance.biometrics.biometricsapi.api.movementData;

import com.derekprovance.biometrics.biometricsapi.api.AbstractApiController;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Date;

@RestController
public class MovementDataController extends AbstractApiController {
    private MovementDataRepository movementDataRepository;
    private final java.util.Date dt = new java.util.Date();
    private final java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final Gson gson = new Gson();

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
        newEntry.setEventTime(sdf.format(dt));

        return movementDataRepository.save(newEntry);
    }

    @RequestMapping(value="/movement-data/date/{startDate}", method=RequestMethod.GET)
    public Iterable<MovementData> getMovementDateByDate(
            @PathVariable(value="startDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date date
    ) {
        return movementDataRepository.findAllByEventTimeBetween(getBeginningOfDay(date), getEndOfDay(date));
    }

    @RequestMapping(value="/movement-data/date/{startDate}/{endDate}", method=RequestMethod.GET)
    public Iterable<MovementData> getMovementDataBetweenDate(
            @PathVariable(value="startDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
            @PathVariable(value="endDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate
    ) {
        return movementDataRepository.findAllByEventTimeBetween(getBeginningOfDay(startDate), getEndOfDay(endDate));
    }
}
