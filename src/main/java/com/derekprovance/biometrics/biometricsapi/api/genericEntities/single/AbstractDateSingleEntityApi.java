package com.derekprovance.biometrics.biometricsapi.api.genericEntities.single;

import com.derekprovance.biometrics.biometricsapi.api.AbstractDataTrackingApi;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;

abstract public class AbstractDateSingleEntityApi extends AbstractDataTrackingApi {
    protected abstract CrudSingleRepository<?, Integer> getRepository();

    @RequestMapping(value="/date/{date}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getByDate(
            @PathVariable(value="date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        final Object entity = getRepository().findByDate(date);

        if(entity == null) {
            throw new EntityNotFoundException("Not Found: " + date);
        }

        return ResponseEntity.ok().body(gson.toJson(entity));
    }

    @RequestMapping(value="/date/{startDate}/{endDate}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<?> getBetweenDate(
            @PathVariable(value="startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @PathVariable(value="endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        return getRepository().findByDateBetween(startDate, endDate);
    }
}
