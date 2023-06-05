package com.group6.moneymanagementbooking.util;

import org.springframework.security.core.context.SecurityContextHolder;

import com.group6.moneymanagementbooking.model.account.dto.UserAccount;

public class SercurityUtil {
    public static UserAccount getPrincipal() {
        return (UserAccount) (SecurityContextHolder.getContext()).getAuthentication().getPrincipal();
    }
}
