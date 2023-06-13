package com.group6.moneymanagementbooking.dto.response;


import lombok.Builder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UsersDTOResponse {
    private String first_name;

    private String last_name;

    private String email;

    private String phone;

    private String address;

    private String role;

    private boolean is_active;

    private double annually_spending;

    private double monthly_spending;

    private double monthly_saving;

    private double monthly_earning;

    private String currency;
    
}
