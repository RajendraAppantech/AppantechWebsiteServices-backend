package com.web.controlles;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;

import com.web.models.CommonResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException .class)
    //@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public CommonResponse handleValidationExceptions(MethodArgumentNotValidException ex) {
    	CommonResponse resp = new CommonResponse();
        Map<String, Object> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        resp.setStatus(false);
        resp.setMessage("INVALID_DETAILS");
        resp.setRespCode("01");
        resp.setData("errorFields", errors);
        return resp;
    }
    
    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseBody
    public ResponseEntity<?> handleUnauthorizedExceptions(HttpClientErrorException ex) {
    	CommonResponse resp = new CommonResponse();
        resp.setStatus(false);
        resp.setMessage(ex.getMessage());
        resp.setRespCode("403");
        resp.setData("errorFields", ex.getMessage());
        return new ResponseEntity<>(resp, HttpStatus.FORBIDDEN);
    }
}