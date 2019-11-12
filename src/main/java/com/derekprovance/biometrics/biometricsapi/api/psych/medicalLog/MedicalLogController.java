package com.derekprovance.biometrics.biometricsapi.api.psych.medicalLog;

import com.derekprovance.biometrics.biometricsapi.api.generic.date.AbstractDateMultipleEntityApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/medical-log")
public class MedicalLogController extends AbstractDateMultipleEntityApi {
    private final MedicalLogRepository medicalLogRepository;

    @Autowired
    public MedicalLogController(MedicalLogRepository medicalLogRepository) {
        this.medicalLogRepository = medicalLogRepository;
    }

    @RequestMapping(value = "", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public Iterable<MedicalLog> getActiveMedicalLogEntries() {
        return medicalLogRepository.findAllByStatus(Status.ACTIVE);
    }

    @PostMapping("")
    public MedicalLog newMedicalLogEntry(@RequestBody MedicalLog newEntry) {
        if(newEntry.getDate() == null) {
            newEntry.setDate(LocalDate.now());
        }

        newEntry.setStatus(Status.ACTIVE);

        return medicalLogRepository.save(newEntry);
    }

    protected MedicalLogRepository getRepository() {
        return this.medicalLogRepository;
    }
}
