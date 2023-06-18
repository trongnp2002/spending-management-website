package com.group6.moneymanagementbooking.dto.mapper;


import com.group6.moneymanagementbooking.dto.request.UsersDTORegisterRequest;
import com.group6.moneymanagementbooking.dto.response.UsersDTOResponse;
import com.group6.moneymanagementbooking.dto.response.UsersForAdminDTOResponse;
import com.group6.moneymanagementbooking.enity.Users;

public class UsersMapper {
    public static UsersDTOResponse toAccountDTOResponse(Users account){
        return UsersDTOResponse.builder().first_name(account.getFirstName()).last_name(account.getLastName())
        .email(account.getEmail()).phone(account.getPhone()).address(account.getAddress())
        .role(account.getRole()).is_active(account.is_active()).currency(account.getCurrency())
        .annually_spending(account.getAnnually_spending()).monthly_earning(account.getMonthly_earning())
        .monthly_saving(account.getMonthly_saving()).monthly_spending(account.getMonthly_spending()).build();
    }

    public static Users toUsers(UsersDTORegisterRequest accountDTORegister){
        return  Users.builder().firstName(accountDTORegister.getFirstName()).lastName(accountDTORegister.getLastName()).email(accountDTORegister.getEmail())
        .password(accountDTORegister.getPassword()).currency("$").phone(accountDTORegister.getPhone()).is_active(true).account_non_locked(true).address(accountDTORegister.getAddress()).is_active(true).role("ROLE_USER").build();
    }

    public static UsersForAdminDTOResponse toUsersForAdminDTOResponse (Users users){
        return UsersForAdminDTOResponse.builder().id(users.getId()).name(users.getFirstName()+" "+users.getLastName())
        .email(users.getEmail()).phone(users.getPhone()).address(users.getAddress()).failedAttempt(users.getFailed_attempt())
        .accountNonLocked(users.isAccount_non_locked()).lockTime(users.getLockTime()).active(users.is_active()).build();
    }

}
