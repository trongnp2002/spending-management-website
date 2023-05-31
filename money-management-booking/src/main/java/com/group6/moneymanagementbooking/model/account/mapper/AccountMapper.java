package com.group6.moneymanagementbooking.model.account.mapper;

import com.group6.moneymanagementbooking.enity.Account;
import com.group6.moneymanagementbooking.model.account.dto.AccountDTORegister;
import com.group6.moneymanagementbooking.model.account.dto.AccountDTOResponse;

public class AccountMapper {
    public static AccountDTOResponse toAccountDTOResponse(Account account){
        return  AccountDTOResponse.builder().address(account.getAccountAddress()).email(account.getAccountEmail()).name(account.getAccountName()).phone(account.getAccountPhone()).build();
    }

    public static Account toAccount(AccountDTORegister accountDTORegister){
        return  Account.builder().accountName(accountDTORegister.getName()).accountEmail(accountDTORegister.getEmail())
        .password(accountDTORegister.getPassword()).accountPhone(accountDTORegister.getPhone()).accountAddress(accountDTORegister.getAddress()).is_Active(true).is_Admin(false).build();
    }
}
