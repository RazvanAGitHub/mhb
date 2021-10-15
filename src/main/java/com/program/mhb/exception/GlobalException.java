package com.program.mhb.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.security.auth.login.AccountNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Log4j2
@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(AccountException.class)
    public ResponseEntity<Object> handleAccountException(AccountException ex, WebRequest webRequest) {
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
        body.put("message", ex.getLocalizedMessage()); // all of the errors concatenated!

        // TODO(razvan) construct a map key for each exception (easily interpretable by any FE tech)
        // TODO(razvan) you can add whatever you consider (it is just an example)
        for (ConstraintViolation<?> constraintViolation : ex.getConstraintViolations()) {
            System.out.println("###" + constraintViolation);
            log.error("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ " + constraintViolation);
        }


        return new ResponseEntity<>(body, HttpStatus.NOT_ACCEPTABLE);//HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleAccountException(NotFoundException ex, WebRequest webRequest) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        body.put("", ex.getStackTrace());
        body.put("", ex.fillInStackTrace());
        body.put("", ex.getCause());
        body.put(" webrequest", webRequest.toString());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}
