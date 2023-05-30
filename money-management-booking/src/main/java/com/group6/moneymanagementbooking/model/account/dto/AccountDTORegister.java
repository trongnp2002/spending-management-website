package com.group6.moneymanagementbooking.model.account.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountDTORegister {
    private String name;
    private String email;
    private String password;
    private String repeatPassword;
    private String phone;
    private String address;
    
}
