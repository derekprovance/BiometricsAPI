package com.derekprovance.biometrics.biometricsapi.api.psych.medicalLog;

import com.derekprovance.biometrics.biometricsapi.api.genericEntities.single.CrudSingleRepository;

import java.util.List;

public interface MedicalLogRepository extends CrudSingleRepository<MedicalLog, Integer> {
    List<MedicalLog> findAllByStatus(Status status);
}