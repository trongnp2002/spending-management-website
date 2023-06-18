package com.group6.moneymanagementbooking.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDTOEditProfileRequest {

    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private String address;
    private String avatar;

}
