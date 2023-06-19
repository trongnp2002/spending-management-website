package com.group6.moneymanagementbooking.model.exception.custom;

import com.group6.moneymanagementbooking.model.CustomError;

public class CustomBadRequestException extends BaseCustomException{

    public CustomBadRequestException(CustomError customError) {
        super(customError);

    }
    
}
