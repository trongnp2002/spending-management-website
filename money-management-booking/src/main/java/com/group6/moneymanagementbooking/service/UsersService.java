package com.group6.moneymanagementbooking.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.group6.moneymanagementbooking.dto.request.UsersDTOForgotPasswordRequest;
import com.group6.moneymanagementbooking.dto.request.UsersDTORegisterRequest;
import com.group6.moneymanagementbooking.model.exception.custom.CustomBadRequestException;

public interface UsersService {

   public String registerAccount(Model model, UsersDTORegisterRequest accountDTORegister)
         throws CustomBadRequestException, Exception;

   public void checkEmailCondition(HttpServletRequest request, HttpServletResponse response)
         throws IOException, CustomBadRequestException;

   public void checkPhoneCondition(HttpServletRequest request, HttpServletResponse response) throws IOException;

   public String forgotPassword(Model model, UsersDTOForgotPasswordRequest usersDTOForgotPasswordRequest);

}
