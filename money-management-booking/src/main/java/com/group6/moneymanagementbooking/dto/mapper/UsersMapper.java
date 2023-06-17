package com.group6.moneymanagementbooking.dto.mapper;


import java.util.Optional;

import com.group6.moneymanagementbooking.dto.request.UserDTOEditProfileRequest;
import com.group6.moneymanagementbooking.dto.request.UsersDTORegisterRequest;
import com.group6.moneymanagementbooking.dto.response.UsersDTOResponse;
import com.group6.moneymanagementbooking.enity.Users;

public class UsersMapper {
    public static UsersDTOResponse toAccountDTOResponse(Users account){
        return UsersDTOResponse.builder().first_name(account.getFirst_name()).last_name(account.getLast_name())
        .email(account.getEmail()).phone(account.getPhone()).address(account.getAddress())
        .role(account.getRole()).is_active(account.is_active()).currency(account.getCurrency())
        .annually_spending(account.getAnnually_spending()).monthly_earning(account.getMonthly_earning())
        .monthly_saving(account.getMonthly_saving()).monthly_spending(account.getMonthly_spending()).build();
    }

    public static Users toUsers(UsersDTORegisterRequest accountDTORegister){
        return  Users.builder().first_name(accountDTORegister.getFirstName()).last_name(accountDTORegister.getLastName()).email(accountDTORegister.getEmail())
        .password(accountDTORegister.getPassword()).phone(accountDTORegister.getPhone()).address(accountDTORegister.getAddress()).is_active(true).role("ROLE_USER").build();
    }

    public static Users toUsers(UserDTOEditProfileRequest userDTOedit){
        return  Users.builder().first_name(userDTOedit.getFirst_name()).last_name(userDTOedit.getLast_name()).email(userDTOedit.getEmail())
        .phone(userDTOedit.getPhone()).address(userDTOedit.getAddress()).avatar(userDTOedit.getAvatar()).build();
    }

    public static UserDTOEditProfileRequest toUserDTOEditProfileRequest(Users optional){
        return  UserDTOEditProfileRequest.builder().first_name(optional.getFirst_name()).last_name(optional.getLast_name()).email(optional.getEmail())
        .phone(optional.getPhone()).address(optional.getAddress()).avatar(optional.getAvatar()).build();
    }

}
