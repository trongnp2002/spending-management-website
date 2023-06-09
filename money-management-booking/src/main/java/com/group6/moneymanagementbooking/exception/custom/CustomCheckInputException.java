package com.group6.moneymanagementbooking.exception.custom;


import com.group6.moneymanagementbooking.model.CustomError;

public class CustomCheckInputException extends BaseCustomException{
    public CustomCheckInputException(CustomError customError) {
        super(customError);
    }

   


    
}
