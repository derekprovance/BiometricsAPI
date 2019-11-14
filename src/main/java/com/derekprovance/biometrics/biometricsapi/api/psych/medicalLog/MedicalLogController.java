package com.derekprovance.biometrics.biometricsapi.api.psych.medicalLog;

import com.derekprovance.biometrics.biometricsapi.api.AbstractBioDateMultipleEntityApi;
import com.derekprovance.biometrics.biometricsapi.database.entity.MedicalLog;
import com.derekprovance.biometrics.biometricsapi.database.repository.MedicalLogRepositoryGeneric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/medical-log")
public class MedicalLogController extends AbstractBioDateMultipleEntityApi {
    private final MedicalLogRepositoryGeneric medicalLogRepository;

    @Autowired
    public MedicalLogController(MedicalLogRepositoryGeneric medicalLogRepository) {
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

    protected MedicalLogRepositoryGeneric getRepository() {
        return this.medicalLogRepository;
    }
}
