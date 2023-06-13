package com.group6.moneymanagementbooking.model.exception.custom;

import com.group6.moneymanagementbooking.model.CustomError;

public class CustomCheckInputException extends BaseCustomException{
    public CustomCheckInputException(CustomError customError) {
        super(customError);
    }

   


    
}
