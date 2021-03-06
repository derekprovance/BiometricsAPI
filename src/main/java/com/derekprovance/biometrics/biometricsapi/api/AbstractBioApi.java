package com.derekprovance.biometrics.biometricsapi.api;

import org.springframework.data.repository.CrudRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityNotFoundException;

abstract public class AbstractBioApi extends AbstractApiController {
    protected abstract CrudRepository<?, Integer> getRepository();

    @RequestMapping(value = "/{requestId}", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> get(@PathVariable Integer requestId) {
        final Object entity = getRepository().findById(requestId)
                .orElseThrow(() -> new EntityNotFoundException("Not Found: " + requestId.toString()));

        return ResponseEntity.ok().body(entity);
    }
}
