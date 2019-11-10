package com.derekprovance.biometrics.biometricsapi.api.genericEntities.range;

import com.derekprovance.biometrics.biometricsapi.api.AbstractDataTrackingApi;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;

abstract public class AbstractDateTimeMultipleEntityApi extends AbstractDataTrackingApi {
    protected abstract CrudRangeRepository<?, Integer> getRepository();

    @RequestMapping(value="/date/{date}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<?> getByDate(
            @PathVariable(value="date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return getRepository().findByDatetimeBetween(date.atStartOfDay(), date.atTime(LocalTime.MAX));
    }

    @RequestMapping(value="/date/{startDate}/{endDate}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<?> getByDateBetween(
            @PathVariable(value="startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @PathVariable(value="endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        return getRepository().findByDatetimeBetween(startDate.atStartOfDay(), endDate.atTime(LocalTime.MAX));
    }
}
