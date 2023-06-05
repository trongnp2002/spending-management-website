package com.group6.moneymanagementbooking.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group6.moneymanagementbooking.exception.custom.CustomBadRequestException;
import com.group6.moneymanagementbooking.model.account.dto.AccountDTOLoginRequest;
import com.group6.moneymanagementbooking.model.account.dto.AccountDTORegister;
import com.group6.moneymanagementbooking.model.account.dto.AccountDTOResponse;

public interface AccountService {

   public String registerAccount(AccountDTORegister accountDTORegister) throws CustomBadRequestException, Exception;

   public String loginAccount(HttpServletRequest request, HttpServletResponse response,
         AccountDTOLoginRequest accountDTOLoginRequest) throws CustomBadRequestException;

   public void checkEmail(HttpServletRequest request, HttpServletResponse response) throws IOException;

}
