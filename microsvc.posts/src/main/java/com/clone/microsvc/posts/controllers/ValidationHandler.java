package com.clone.microsvc.posts.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        Map<String, String> map= new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) ->{

            String fieldTitle=  ((FieldError) error).getField();
            String fieldDescription= ((FieldError) error).getField();
            String message= error.getDefaultMessage();

            map.put(fieldTitle,message);
            map.put(fieldDescription,message);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
    }
}
