package com.group6.moneymanagementbooking.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.group6.moneymanagementbooking.dto.request.UsersDTOForgotPasswordRequest;
import com.group6.moneymanagementbooking.dto.request.UsersDTORegisterRequest;
import com.group6.moneymanagementbooking.enity.Users;

public interface UsersService {

      public String userRegister(Model model, UsersDTORegisterRequest accountDTORegister)
                  throws Exception;

      public void checkEmailCondition(HttpServletRequest request, HttpServletResponse response)
                  throws IOException;

      public void checkPhoneCondition(HttpServletRequest request, HttpServletResponse response) throws IOException;

      public String forgotPassword(Model model, UsersDTOForgotPasswordRequest usersDTOForgotPasswordRequest);

      public Users getUserByEmail(String email);

      public Users getUsers();

      public void addAdjustForUser(Users users, Model model);

}
