package com.clone.microsvc.users.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

public class ValidationHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        Map<String, String> stringMap= new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) ->{

            String fieldEmail= ((FieldError) error).getField();
            String fieldUserName= ((FieldError) error).getField();
            String fieldPassword= ((FieldError) error).getField();
            String answer= error.getDefaultMessage();

            stringMap.put(fieldEmail,answer);
            stringMap.put(fieldUserName,answer);
            stringMap.put(fieldPassword,answer);


        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(stringMap);
    }
}
