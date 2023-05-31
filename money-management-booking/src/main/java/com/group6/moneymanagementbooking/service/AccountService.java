package com.group6.moneymanagementbooking.service;

import com.group6.moneymanagementbooking.exception.custom.CustomBadRequestException;
import com.group6.moneymanagementbooking.model.account.dto.AccountDTOLoginRequest;
import com.group6.moneymanagementbooking.model.account.dto.AccountDTORegister;
import com.group6.moneymanagementbooking.model.account.dto.AccountDTOResponse;

public interface AccountService {

   public AccountDTOResponse registerAccount(AccountDTORegister accountDTORegister) throws CustomBadRequestException;

   public AccountDTOResponse loginAccount(AccountDTOLoginRequest accountDTOLoginRequest) throws CustomBadRequestException;
    
}
