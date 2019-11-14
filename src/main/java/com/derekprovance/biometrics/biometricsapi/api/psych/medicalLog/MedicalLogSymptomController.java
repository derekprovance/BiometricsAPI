package com.derekprovance.biometrics.biometricsapi.api.psych.medicalLog;

import com.derekprovance.biometrics.biometricsapi.api.AbstractBioApi;
import com.derekprovance.biometrics.biometricsapi.database.entity.MedicalLog;
import com.derekprovance.biometrics.biometricsapi.database.entity.Symptom;
import com.derekprovance.biometrics.biometricsapi.database.repository.MedicalLogRepository;
import com.derekprovance.biometrics.biometricsapi.database.repository.SymptomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/medical-log/{logId}/symptom")
public class MedicalLogSymptomController extends AbstractBioApi {
    private final SymptomRepository symptomRepository;
    private final MedicalLogRepository medicalLogRepository;

    @Autowired
    public MedicalLogSymptomController(SymptomRepository symptomRepository, MedicalLogRepository medicalLogRepository) {
        this.symptomRepository = symptomRepository;
        this.medicalLogRepository = medicalLogRepository;
    }

    @RequestMapping(value = "", method= RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE)
    public Symptom addSymptom(@PathVariable Integer logId, @RequestBody Symptom newEntry) {
        MedicalLog log = medicalLogRepository.findById(logId)
                .orElseThrow(() -> new EntityNotFoundException("Not Found: " + logId.toString()));

        newEntry.setMedicalLog(log);
        return symptomRepository.save(newEntry);
    }

    @RequestMapping(value = "/{symptomId}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
    public void removeSymptom(@PathVariable Integer logId, @PathVariable Integer symptomId) {
        Symptom symptom = getSymptomIfExistsInMedicalLog(logId, symptomId);
        symptomRepository.deleteById(symptom.getId());
    }

    @RequestMapping(value = "/{symptomId}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public Symptom getSpecificSymptom(@PathVariable Integer logId, @PathVariable Integer symptomId) {
        return getSymptomIfExistsInMedicalLog(logId, symptomId);
    }

    private Symptom getSymptomIfExistsInMedicalLog(Integer logId, Integer symptomId) {
        final MedicalLog log = medicalLogRepository.findById(logId)
                .orElseThrow(() -> new EntityNotFoundException("Not Found: " + logId.toString()));

        final Symptom symptom = symptomRepository.findById(symptomId)
                .orElseThrow(() -> new EntityNotFoundException("Not Found: " + symptomId.toString()));

        if(log.getSymptoms().contains(symptom)) {
            return symptom;
        }

        throw new EntityNotFoundException("Not Found: " + symptomId.toString());
    }

    @Override
    protected SymptomRepository getRepository() {
        return symptomRepository;
    }
}
