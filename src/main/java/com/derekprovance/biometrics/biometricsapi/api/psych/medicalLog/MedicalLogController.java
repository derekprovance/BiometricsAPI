package com.derekprovance.biometrics.biometricsapi.api.psych.medicalLog;

import com.derekprovance.biometrics.biometricsapi.api.AbstractBioDateMultipleEntityApi;
import com.derekprovance.biometrics.biometricsapi.database.entity.MedicalLog;
import com.derekprovance.biometrics.biometricsapi.database.entity.Symptom;
import com.derekprovance.biometrics.biometricsapi.database.repository.MedicalLogRepository;
import com.derekprovance.biometrics.biometricsapi.database.repository.SymptomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/medical-log")
public class MedicalLogController extends AbstractBioDateMultipleEntityApi {
    private final MedicalLogRepository medicalLogRepository;
    private final SymptomRepository symptomRepository;

    @Autowired
    public MedicalLogController(MedicalLogRepository medicalLogRepository, SymptomRepository symptomRepository) {
        this.medicalLogRepository = medicalLogRepository;
        this.symptomRepository = symptomRepository;
    }

    @RequestMapping(value = "", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
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

    @RequestMapping(value = "/{logId}/symptom", method= RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE)
    public Symptom addSymptom(@PathVariable Integer logId, @RequestBody Symptom newEntry) {
        MedicalLog log = medicalLogRepository.findById(logId)
                .orElseThrow(() -> new EntityNotFoundException("Not Found: " + logId.toString()));

        newEntry.setMedicalLog(log);
        return symptomRepository.save(newEntry);
    }

    @RequestMapping(value = "/{logId}/symptom/{symptomId}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
    public void removeSymptom(@PathVariable Integer logId, @PathVariable Integer symptomId) {
        final MedicalLog log = medicalLogRepository.findById(logId)
                .orElseThrow(() -> new EntityNotFoundException("Not Found: " + logId.toString()));

        final Symptom symptom = symptomRepository.findById(symptomId)
                .orElseThrow(() -> new EntityNotFoundException("Not Found: " + symptomId.toString()));

        if(log.getSymptoms().contains(symptom)) {
            symptomRepository.deleteById(symptom.getId());
        }
    }

    @RequestMapping(value = "/{logId}/symptom/{symptomId}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public void getSpecificSymptom(@PathVariable Integer logId, @PathVariable Integer symptomId) {
        //TODO - implement
    }

    protected MedicalLogRepository getRepository() {
        return this.medicalLogRepository;
    }
}
