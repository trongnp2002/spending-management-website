package com.group6.moneymanagementbooking.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import com.group6.moneymanagementbooking.dto.request.UserDTOEditProfileRequest;
import com.group6.moneymanagementbooking.dto.request.UsersDTORegisterRequest;
import com.group6.moneymanagementbooking.dto.response.UsersDTOResponse;
import com.group6.moneymanagementbooking.dto.response.UsersForAdminDTOResponse;
import com.group6.moneymanagementbooking.enity.Users;

public class UsersMapper {
    public static UsersDTOResponse toUserDTOResponse(Users account) {
        return UsersDTOResponse.builder().firstName(account.getFirstName()).lastName(account.getLastName())
                .email(account.getEmail()).phone(account.getPhone()).address(account.getAddress())
                .role(account.getRole()).isActive(account.isActive()).currency(account.getCurrency())
                .annuallySpending(account.getAnnually_spending()).monthlyEarning(account.getMonthly_earning())
                .monthlySaving(account.getMonthly_saving()).monthlySpending(account.getMonthly_spending()).build();
    }

    public static Users toUsers(UsersDTORegisterRequest accountDTORegister) {
        return Users.builder().firstName(accountDTORegister.getFirstName())
                .lastName(accountDTORegister.getLastName()).email(accountDTORegister.getEmail())
                .password(accountDTORegister.getPassword()).currency("$").phone(accountDTORegister.getPhone())
                .active(true).nonLocked(true).address(accountDTORegister.getAddress())
                .active(true).role("ROLE_USER").avatar("avata1").build();
    }

    public static UsersForAdminDTOResponse toUsersForAdminDTOResponse(Users users) {
        return UsersForAdminDTOResponse.builder().id(users.getId())
                .name(users.getFirstName() + " " + users.getLastName())
                .email(users.getEmail()).phone(users.getPhone()).address(users.getAddress())
                .failedAttempt(users.getFailed_attempt()).role(users.getRole())
                .accountNonLocked(users.isNonLocked()).lockTime(users.getLockTime()).active(users.isActive()).build();
    }

    public static UserDTOEditProfileRequest toUserDTOEditProfileRequest(Users users){
        return UserDTOEditProfileRequest.builder().address(users.getAddress()).first_name(users.getFirstName())
        .last_name(users.getLastName()).email(users.getEmail()).avatar(users.getAvatar()).phone(users.getPhone()).build();
    }

    public static Users toUsers(UserDTOEditProfileRequest userDTOedit) {
        return Users.builder().firstName(userDTOedit.getFirst_name()).lastName(userDTOedit.getLast_name())
                .email(userDTOedit.getEmail())
                .phone(userDTOedit.getPhone()).address(userDTOedit.getAddress()).avatar(userDTOedit.getAvatar())
                .build();
    }

  

    public static List<UsersForAdminDTOResponse> toUsersForAdminDTOResponses(List<Users> users) {
        List<UsersForAdminDTOResponse> list = new ArrayList<UsersForAdminDTOResponse>();
        for (Users user : users) {
            list.add(UsersMapper.toUsersForAdminDTOResponse(user));
        }
        if (list.size() < 10) {
            for (int i = list.size() ; i < 10; i++) {
                list.add(UsersForAdminDTOResponse.builder().id(0).name(null).build());
            }
        }
        return list;
    }

}
