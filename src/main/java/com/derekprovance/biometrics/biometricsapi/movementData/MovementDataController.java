package com.derekprovance.biometrics.biometricsapi.movementData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class MovementDataController {
    @Autowired
    private MovementDataRepository movementDataRepository;
    private java.util.Date dt = new java.util.Date();
    private java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @RequestMapping(name="/movement-data/", method=GET)
    public Iterable<MovementData> getMovementData() {
        return movementDataRepository.findAll();
    }

    @GetMapping("/movement-data/{id}")
    public MovementData getSingleMovementDataEntry(@PathVariable Integer id) {
        return movementDataRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id.toString()));
    }

    @PostMapping("/movement-data")
    public MovementData newMovementDataEntry(@RequestBody MovementData newEntry) {
        newEntry.setEventTime(sdf.format(dt));

        return movementDataRepository.save(newEntry);
    }
}
