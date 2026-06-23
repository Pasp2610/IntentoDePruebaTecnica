package com.prueba.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ClientNotFound.class)
    public ResponseEntity<Map<String ,Object>>handleTaskException(ClientNotFound ex){
        Map<String,Object>error= new HashMap<>();
        error.put("status", HttpStatus.NOT_FOUND.value());
        error.put("status",ex.getMessage());
        error.put("status", LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
    @ExceptionHandler(MovieNotFound.class)
    public ResponseEntity<Map<String,Object>>handleMovieNotFoundException(MovieNotFound ex){
        Map<String,Object>error= new HashMap<>();
        error.put("status",HttpStatus.NOT_FOUND.value());
        error.put("status",ex.getMessage());
        error.put("status",LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    };
}

