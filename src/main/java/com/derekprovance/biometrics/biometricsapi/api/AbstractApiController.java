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

    private Calendar cal = Calendar.getInstance();
    protected final Gson gson = new Gson();
    protected final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public AbstractApiController() {
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    protected Date getBeginningOfDay(Date date) {
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();

    }

    protected Date getEndOfDay(Date date) {
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    @ResponseBody
    @ResponseStatus(value=HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = { ConversionFailedException.class, IllegalArgumentException.class })
    public Map<String, Object> unsupportedMediaTypeException(MethodArgumentTypeMismatchException e) {
        Map<String, Object> message = new HashMap<>();
        message.put("message", String.format("%s is invalid: %s", e.getName(), e.getValue()));
        return message;
    }
}
