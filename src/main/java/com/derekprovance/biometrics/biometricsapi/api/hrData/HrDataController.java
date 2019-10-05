package com.derekprovance.biometrics.biometricsapi.api.hrData;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
public class HrDataController {
    private HrDataRepository hrDataRepository;
    private final java.util.Date dt = new java.util.Date();
    private final java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final Gson gson = new Gson();

    @Autowired
    public HrDataController(HrDataRepository hrDataRepository) {
        this.hrDataRepository = hrDataRepository;
    }

    @RequestMapping(value="/hr-data/{id}", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getSingleHrDataEntry(@PathVariable Integer id) {
        try {
            final HrData hrData = hrDataRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Not Found: " + id.toString()));

            return ResponseEntity.ok().body(gson.toJson(hrData));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/hr-data")
    public HrData newHrDataEntry(@RequestBody HrData newEntry) {
        newEntry.setEventTime(sdf.format(dt));

        return hrDataRepository.save(newEntry);
    }
}
