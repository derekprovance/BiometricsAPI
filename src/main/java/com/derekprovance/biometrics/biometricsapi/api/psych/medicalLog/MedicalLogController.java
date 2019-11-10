package com.derekprovance.biometrics.biometricsapi.api.psych.medicalLog;

import com.derekprovance.biometrics.biometricsapi.api.genericEntities.single.AbstractDateSingleEntityApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Objects;

@RestController
@RequestMapping("/medical-log")
public class MedicalLogController extends AbstractDateSingleEntityApi {
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

        final MedicalLog existingDateEntry = (MedicalLog) medicalLogRepository.findByDate(newEntry.getDate());
        if(existingDateEntry != null) {
            newEntry.setId(existingDateEntry.getId());
        }

        return medicalLogRepository.save(Objects.requireNonNullElse(existingDateEntry, newEntry));
    }

    protected MedicalLogRepository getRepository() {
        return this.medicalLogRepository;
    }
}
