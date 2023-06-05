package com.group6.moneymanagementbooking.model.account.dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTOLoginRequest{
    private String email;
    private String password;

    
}
