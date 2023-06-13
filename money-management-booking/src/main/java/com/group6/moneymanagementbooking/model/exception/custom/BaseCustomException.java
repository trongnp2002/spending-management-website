package com.group6.moneymanagementbooking.model.exception.custom;



import java.util.HashMap;
import java.util.Map;

import com.group6.moneymanagementbooking.model.CustomError;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseCustomException extends Exception{
    private Map<String, CustomError> errors;

    public BaseCustomException(CustomError customError){
        errors = new HashMap<>();
        errors.put("errors", customError);
    }
}
