package com.derekprovance.biometrics.biometricsapi.api.psych.medicalLog;

import com.derekprovance.biometrics.biometricsapi.api.genericEntities.date.CrudDateRepository;

import java.util.List;

public interface MedicalLogRepository extends CrudDateRepository<MedicalLog, Integer> {
    List<MedicalLog> findAllByStatus(Status status);
}