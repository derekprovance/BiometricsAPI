package com.derekprovance.biometrics.biometricsapi.api.generic.date;

import com.derekprovance.biometrics.biometricsapi.api.AbstractDataTrackingApi;
import com.derekprovance.biometrics.biometricsapi.database.repository.GenericCrudDateRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;

abstract class AbstractDateApi extends AbstractDataTrackingApi {

    protected abstract GenericCrudDateRepository<?, Integer> getRepository();

    @RequestMapping(value="/date/{startDate}/{endDate}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<?> getBetweenDate(
            @PathVariable(value="startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @PathVariable(value="endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        return getRepository().findByDateBetween(startDate, endDate);
    }
}
