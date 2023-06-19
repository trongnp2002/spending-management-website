package com.group6.moneymanagementbooking.model.exception;


import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.group6.moneymanagementbooking.model.CustomError;
import com.group6.moneymanagementbooking.model.exception.custom.CustomBadRequestException;





@RestControllerAdvice
public class APIExceptionHandler {
    
    @ExceptionHandler(CustomBadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Map<String, CustomError> badRequestException(CustomBadRequestException customBadRequestException){
        return customBadRequestException.getErrors();
    }


    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Map<String, String> badRequestException(ConstraintViolationException constraintViolationException){
        Map<String, String> errorMap = new HashMap<>();
        constraintViolationException.getConstraintViolations().forEach(error -> {
            errorMap.put(error.getPropertyPath().toString(), error.getMessage());
        });
        return errorMap;
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Map<String, String> badRequestException(MethodArgumentNotValidException methodArgumentNotValidException){
        Map<String, String> errorMap = new HashMap<>();
        methodArgumentNotValidException.getFieldErrors().forEach(error -> {
            errorMap.put(error.getField().toString(), error.getDefaultMessage());
        });
        return errorMap;
    }
}
