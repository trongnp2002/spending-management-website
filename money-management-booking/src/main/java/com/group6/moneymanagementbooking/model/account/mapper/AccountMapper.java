package com.group6.moneymanagementbooking.model.account.mapper;

import com.group6.moneymanagementbooking.enity.Account;
import com.group6.moneymanagementbooking.model.account.dto.AccountDTOResponse;

public class AccountMapper {
    public static AccountDTOResponse toAccountDTOResponse(Account account){
        return  AccountDTOResponse.builder().address(account.getAddress()).email(account.getEmail()).name(account.getName()).build();
    }
}
