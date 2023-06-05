package com.group6.moneymanagementbooking.model.account.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AccountDTORegister {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String repeatPassword;
    private String phone;
    private String address;
    

    
}
