package com.clone.microsvc.categories.controllers;

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
        Map<String, String> errors= new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) ->{     //La ruta que me envía spring boot para entrar al método

            String fieldName= ((FieldError) error).getField();      //Definiendo la variable y el valor que voy a mandar a la "clave" del Map
            String errorMessage= error.getDefaultMessage();         //El message que definí en la anotación

            errors.put(fieldName,errorMessage);                     //Aquí estoy añadiendo esos valores a el Map

        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
