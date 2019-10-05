package com.derekprovance.biometrics.biometricsapi.api.hrData;

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
public class HrDataController extends AbstractApiController {
    private HrDataRepository hrDataRepository;
    private final Gson gson = new Gson();

    @Autowired
    public HrDataController(HrDataRepository hrDataRepository) {
        this.hrDataRepository = hrDataRepository;
    }

    @RequestMapping(value="/hr-data/{startDate}", method=RequestMethod.GET)
    public Iterable<HrData> getHrDataByDate(
            @PathVariable(value="startDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date date
    ) {
        return hrDataRepository.findAllByEventTimeBetween(getBeginningOfDay(date), getEndOfDay(date));
    }

    @RequestMapping(value="/hr-data/{startDate}/{endDate}", method=RequestMethod.GET)
    public Iterable<HrData> getHrDataBetweenDate(
            @PathVariable(value="startDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
            @PathVariable(value="endDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate
    ) {
        return hrDataRepository.findAllByEventTimeBetween(getBeginningOfDay(startDate), getEndOfDay(endDate));
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
        newEntry.setEventTime(new Date());

        return hrDataRepository.save(newEntry);
    }
}
