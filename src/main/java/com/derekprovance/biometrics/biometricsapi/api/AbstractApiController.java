package com.derekprovance.biometrics.biometricsapi.api;

import com.google.common.base.CaseFormat;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.persistence.EntityNotFoundException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractApiController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    protected final Gson gson = new Gson();
    private final Pattern pattern = Pattern.compile("'(.+?)'", Pattern.DOTALL);

    private static final String MISSING_SQL_PARAMETER_STRING = "Parameter %s is required";

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
    public ResponseEntity<String> requestHandlingEntityNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(value= HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<?> requestHandlingSQLIntegrityConstraint(DataIntegrityViolationException ex) {
        Map<String, Object> message = new HashMap<>();

        String messageStr = getMessageFromDataIntegrityException(ex);

        message.put("message", messageStr);
        return ResponseEntity.badRequest().body(message);
    }

    private String getMessageFromDataIntegrityException(DataIntegrityViolationException ex) {
        String messageStr = "";

        final Throwable cause = ex.getCause();
        if(cause != null) {
            final Throwable cause1 = cause.getCause();

            if(cause1 != null && cause1.getClass() == SQLIntegrityConstraintViolationException.class) {
                messageStr = String.format(
                        MISSING_SQL_PARAMETER_STRING,
                        getColumnFromSQLIntegrityConstraintViolationException((SQLIntegrityConstraintViolationException) cause1)
                );
            }
        } else {
            messageStr = ex.getMessage();
        }

        return messageStr;
    }

    private String getColumnFromSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex) {
        String column;

        column = ex.getMessage();
        final Matcher matcher = pattern.matcher(column);
        if(matcher.find()) {
            column = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, matcher.group(1));
        }

        return column;
    }
}
