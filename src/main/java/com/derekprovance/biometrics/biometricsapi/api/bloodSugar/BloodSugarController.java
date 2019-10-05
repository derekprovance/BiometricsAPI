package com.derekprovance.biometrics.biometricsapi.api.bloodSugar;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
public class BloodSugarController {
    private BloodSugarRepository bloodSugarRepository;
    private final java.util.Date dt = new java.util.Date();
    private final java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final Gson gson = new Gson();

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
        newEntry.setDatetime(sdf.format(dt));

        return bloodSugarRepository.save(newEntry);
    }
}
