package com.group6.moneymanagementbooking.model.account.dto;

import lombok.Builder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AccountDTOResponse {
    private String name;
    private String email;
    private String address;

}
