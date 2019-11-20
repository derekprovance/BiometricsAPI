package com.derekprovance.biometrics.biometricsapi.api.utility;

import com.derekprovance.biometrics.biometricsapi.api.AbstractApiController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

//TODO - implement Spring Security
@RestController
@RequestMapping("/authenticate")
public class Authenticate extends AbstractApiController {
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> authenticate() {

        Map<String, Object> map = new HashMap<>();
        map.put("status", "OK");

        return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(map));
    }
}
