package com.derekprovance.biometrics.biometricsapi.api.dataTracking.hrData;

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
@RequestMapping("/hr-data")
public class HrDataController extends AbstractApiController {
    private final HrDataRepository hrDataRepository;

    @Autowired
    public HrDataController(HrDataRepository hrDataRepository) {
        this.hrDataRepository = hrDataRepository;
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getSingleHrDataEntry(@PathVariable Integer id) {
        final HrData hrData = hrDataRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not Found: " + id.toString()));

        return ResponseEntity.ok().body(gson.toJson(hrData));
    }

    @PostMapping("")
    public HrData newHrDataEntry(@RequestBody HrData newEntry) {
        newEntry.setEventTime(LocalDateTime.now());

        return hrDataRepository.save(newEntry);
    }

    @RequestMapping(value="/date/{date}", method=RequestMethod.GET)
    public Iterable<HrData> getHrDataByDate(
            @PathVariable(value="date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return hrDataRepository.findAllByEventTimeBetween(date.atStartOfDay(), date.atTime(LocalTime.MAX));
    }

    @RequestMapping(value="/date/{startDate}/{endDate}", method=RequestMethod.GET)
    public Iterable<HrData> getHrDataBetweenDate(
            @PathVariable(value="startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @PathVariable(value="endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        return hrDataRepository.findAllByEventTimeBetween(startDate.atStartOfDay(), endDate.atTime(LocalTime.MAX));
    }
}
