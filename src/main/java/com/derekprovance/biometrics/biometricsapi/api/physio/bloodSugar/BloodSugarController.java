package com.derekprovance.biometrics.biometricsapi.api.physio.bloodSugar;

import com.derekprovance.biometrics.biometricsapi.api.generic.datetime.AbstractDateTimeMultipleEntityApi;
import com.derekprovance.biometrics.biometricsapi.api.generic.DateTimeEntity;
import com.derekprovance.biometrics.biometricsapi.services.bloodSugarAnalysis.BloodSugarAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/blood-sugar")
public class BloodSugarController extends AbstractDateTimeMultipleEntityApi {
    private final BloodSugarRepository bloodSugarRepository;
    private final BloodSugarAnalysisService bloodSugarAnalysisService;

    @Autowired
    public BloodSugarController(BloodSugarRepository bloodSugarRepository, BloodSugarAnalysisService bloodSugarAnalysisService) {
        this.bloodSugarRepository = bloodSugarRepository;
        this.bloodSugarAnalysisService = bloodSugarAnalysisService;
    }

    @RequestMapping(value = "/{requestId}/analyze", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> analyze(@PathVariable Integer requestId) {
        final BloodSugar entity = this.bloodSugarRepository.findById(requestId)
                .orElseThrow(() -> new EntityNotFoundException("Not Found: " + requestId.toString()));

        return ResponseEntity.ok().body(gson.toJson(bloodSugarAnalysisService.analyze(entity)));
    }

    @PostMapping("")
    public DateTimeEntity newEntry(@RequestBody BloodSugar newEntry) {
        if(newEntry.getDatetime() == null) {
            newEntry.setDatetime(LocalDateTime.now());
        }

        return bloodSugarRepository.save(newEntry);
    }

    protected BloodSugarRepository getRepository() {
        return this.bloodSugarRepository;
    }
}
