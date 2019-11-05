package com.derekprovance.biometrics.biometricsapi.api.singleEntity;

import com.derekprovance.biometrics.biometricsapi.api.AbstractDataTrackingApi;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;

abstract public class AbstractSingleEntityApi extends AbstractDataTrackingApi {
    protected abstract CrudSingleRepository<?, Integer> getRepository();

    @RequestMapping(value="/date/{date}", method= RequestMethod.GET)
    public ResponseEntity<String> getByDate(
            @PathVariable(value="date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        final Object entity = getRepository().findByDate(date);

        if(entity == null) {
            throw new EntityNotFoundException("Not Found: " + date);
        }

        return ResponseEntity.ok().body(gson.toJson(entity));
    }

    @RequestMapping(value="/date/{startDate}/{endDate}", method=RequestMethod.GET)
    public Iterable<?> getBetweenDate(
            @PathVariable(value="startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @PathVariable(value="endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        return getRepository().findByDateBetween(startDate.atStartOfDay(), endDate.atTime(LocalTime.MAX));
    }
}
