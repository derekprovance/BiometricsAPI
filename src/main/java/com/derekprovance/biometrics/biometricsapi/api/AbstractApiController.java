package com.derekprovance.biometrics.biometricsapi.api;

import com.google.gson.Gson;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.text.SimpleDateFormat;
import java.util.*;

public abstract class AbstractApiController {

    protected final Gson gson = new Gson();
    protected final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @ResponseBody
    @ResponseStatus(value=HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = { ConversionFailedException.class, IllegalArgumentException.class })
    public Map<String, Object> unsupportedMediaTypeException(MethodArgumentTypeMismatchException e) {
        Map<String, Object> message = new HashMap<>();
        message.put("message", String.format("%s is invalid: %s", e.getName(), e.getValue()));
        return message;
    }
}
