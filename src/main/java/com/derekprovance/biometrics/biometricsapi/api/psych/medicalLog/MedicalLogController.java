package com.derekprovance.biometrics.biometricsapi.api.psych.medicalLog;

import com.derekprovance.biometrics.biometricsapi.api.genericEntities.single.AbstractSingleEntityApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/medical-log")
public class MedicalLogController extends AbstractSingleEntityApi {
    private final MedicalLogRepository medicalLogRepository;

    @Autowired
    public MedicalLogController(MedicalLogRepository medicalLogRepository) {
        this.medicalLogRepository = medicalLogRepository;
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
