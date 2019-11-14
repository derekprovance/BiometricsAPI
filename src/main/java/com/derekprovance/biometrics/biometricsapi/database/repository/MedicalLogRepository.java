package com.derekprovance.biometrics.biometricsapi.database.repository;

import com.derekprovance.biometrics.biometricsapi.api.psych.medicalLog.Status;
import com.derekprovance.biometrics.biometricsapi.database.entity.MedicalLog;

import java.util.List;

public interface MedicalLogRepository extends GenericCrudDateRepository<MedicalLog, Integer> {
    List<MedicalLog> findAllByStatus(Status status);
}