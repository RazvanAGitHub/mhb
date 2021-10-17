package com.program.mhb.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;

import java.util.LinkedHashMap;
import java.util.Map;

@Log4j2
@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(TransactionException.class)
    public ResponseEntity<Object> handleTransactionException(TransactionException ex, WebRequest webRequest) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("code", ex.getCode());
        body.put("message", ex.getMessage());

        log.error(ex.getCode() + " - " + ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest webRequest) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getLocalizedMessage());

        for (ConstraintViolation<?> constraintViolation : ex.getConstraintViolations()) {
            log.error(constraintViolation);
        }

        return new ResponseEntity<>(body, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleTransactionException(NotFoundException ex, WebRequest webRequest) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("code", ex.getCode());
        body.put("message", ex.getMessage());

        log.error(ex.getCode() + " - " + ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}
