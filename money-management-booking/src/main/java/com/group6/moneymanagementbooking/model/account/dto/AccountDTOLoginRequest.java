package com.group6.moneymanagementbooking.model.account.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountDTOLoginRequest {
    private String email;
    private String password;
}
