package com.derekprovance.biometrics.biometricsapi.services.sync.garmin.endpoints;

import com.derekprovance.biometrics.biometricsapi.database.entity.AbstractDateTimeEntity;
import com.derekprovance.biometrics.biometricsapi.database.repository.GenericCrudDateTimeRepository;
import com.derekprovance.biometrics.biometricsapi.services.AbstractService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

abstract class AbstractEndpoint extends AbstractService {
    abstract GenericCrudDateTimeRepository getRepository();

    List<LocalDateTime> getListOfDateTimeEntries(LocalDate date) {
        List<LocalDateTime> dates = new ArrayList<>();
        List<AbstractDateTimeEntity> allByDatetimeBetween = getRepository().findByDatetimeBetween(date.atStartOfDay(), date.atTime(LocalTime.MAX));

        for(AbstractDateTimeEntity heartRate : allByDatetimeBetween) {
            dates.add(heartRate.getDatetime());
        }

        return dates;
    }
}
