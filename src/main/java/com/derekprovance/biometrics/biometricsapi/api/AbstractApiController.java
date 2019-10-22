package com.derekprovance.biometrics.biometricsapi.api;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.persistence.EntityNotFoundException;
import java.text.SimpleDateFormat;
import java.util.*;

public abstract class AbstractApiController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    protected final Gson gson = new Gson();

    @ResponseBody
    @ResponseStatus(value=HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = { ConversionFailedException.class, IllegalArgumentException.class })
    public Map<String, Object> unsupportedMediaTypeException(MethodArgumentTypeMismatchException e) {
        Map<String, Object> message = new HashMap<>();
        message.put("message", String.format("%s is invalid: %s", e.getName(), e.getValue()));
        return message;
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value= HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<String> requestHandlingNoHandlerFound() {
        return ResponseEntity.notFound().build();
    }
}
