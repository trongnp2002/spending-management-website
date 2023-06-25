package com.group6.moneymanagementbooking.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import com.group6.moneymanagementbooking.dto.request.UserDTOEditProfileRequest;
import com.group6.moneymanagementbooking.dto.request.UsersDTORegisterRequest;
import com.group6.moneymanagementbooking.dto.response.UsersDTOResponse;
import com.group6.moneymanagementbooking.dto.response.UsersForAdminDTOResponse;
import com.group6.moneymanagementbooking.enity.Users;

public class UsersMapper {
    public static UsersDTOResponse toAccountDTOResponse(Users account) {
        return UsersDTOResponse.builder().first_name(account.getFirstName()).last_name(account.getLastName())
                .email(account.getEmail()).phone(account.getPhone()).address(account.getAddress())
                .role(account.getRole()).is_active(account.isActive()).currency(account.getCurrency())
                .annually_spending(account.getAnnually_spending()).monthly_earning(account.getMonthly_earning())
                .monthly_saving(account.getMonthly_saving()).monthly_spending(account.getMonthly_spending()).build();
    }

    public static Users toUsers(UsersDTORegisterRequest accountDTORegister) {
        return Users.builder().firstName(accountDTORegister.getFirstName())
                .lastName(accountDTORegister.getLastName()).email(accountDTORegister.getEmail())
                .password(accountDTORegister.getPassword()).currency("$").phone(accountDTORegister.getPhone())
                .active(true).nonLocked(true).address(accountDTORegister.getAddress())
                .active(true).role("ROLE_USER").build();
    }

    public static UsersForAdminDTOResponse toUsersForAdminDTOResponse(Users users) {
        return UsersForAdminDTOResponse.builder().id(users.getId())
                .name(users.getFirstName() + " " + users.getLastName())
                .email(users.getEmail()).phone(users.getPhone()).address(users.getAddress())
                .failedAttempt(users.getFailed_attempt()).role(users.getRole())
                .accountNonLocked(users.isNonLocked()).lockTime(users.getLockTime()).active(users.isActive()).build();
    }

    public static Users toUsers(UserDTOEditProfileRequest userDTOedit) {
        return Users.builder().firstName(userDTOedit.getFirst_name()).lastName(userDTOedit.getLast_name())
                .email(userDTOedit.getEmail())
                .phone(userDTOedit.getPhone()).address(userDTOedit.getAddress()).avatar(userDTOedit.getAvatar())
                .build();
    }

    public static UserDTOEditProfileRequest toUserDTOEditProfileRequest(Users optional) {
        return UserDTOEditProfileRequest.builder().first_name(optional.getFirstName()).last_name(optional.getLastName())
                .email(optional.getEmail())
                .phone(optional.getPhone()).address(optional.getAddress()).avatar(optional.getAvatar()).build();
    }

    public static List<UsersForAdminDTOResponse> toUsersForAdminDTOResponses(List<Users> users) {
        List<UsersForAdminDTOResponse> list = new ArrayList<UsersForAdminDTOResponse>();
        for (Users user : users) {
            list.add(UsersMapper.toUsersForAdminDTOResponse(user));
        }
        if (list.size() < 10) {
            for (int i = list.size() ; i < 10; i++) {
                list.add(UsersForAdminDTOResponse.builder().build());
            }
        }
        return list;
    }

}
