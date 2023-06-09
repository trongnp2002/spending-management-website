package com.group6.moneymanagementbooking.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.group6.moneymanagementbooking.exception.custom.CustomBadRequestException;
import com.group6.moneymanagementbooking.model.account.dto.AccountDTOLoginRequest;
import com.group6.moneymanagementbooking.model.account.dto.AccountDTORegister;

public interface AccountService {

   public String registerAccount(Model model, AccountDTORegister accountDTORegister) throws CustomBadRequestException, Exception;

   public String loginAccount(Model model, HttpServletRequest request, HttpServletResponse response,
         AccountDTOLoginRequest accountDTOLoginRequest) throws CustomBadRequestException;

   public void checkEmail(HttpServletRequest request, HttpServletResponse response) throws IOException, CustomBadRequestException;

public void checkPhone(HttpServletRequest request, HttpServletResponse response) throws IOException;

}
