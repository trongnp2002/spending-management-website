package com.group6.moneymanagementbooking.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.group6.moneymanagementbooking.dto.request.UsersDTOForgotPasswordRequest;
import com.group6.moneymanagementbooking.dto.request.UsersDTOLoginRequest;
import com.group6.moneymanagementbooking.dto.request.UsersDTORegisterRequest;
import com.group6.moneymanagementbooking.model.exception.custom.CustomBadRequestException;
import com.group6.moneymanagementbooking.service.UsersService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@CrossOrigin
public class LoginController {
    private final UsersService userService;
    

   


    @GetMapping("/login")
    public String goToLoginPage (Model model) throws CustomBadRequestException {
        UsersDTOLoginRequest accountDTOLoginRequest =  UsersDTOLoginRequest.builder().build();
        model.addAttribute("usersDTOLoginRequest", accountDTOLoginRequest);
        model.addAttribute("report", "");
        return "login";
    }


    @GetMapping("/register")
    public String registerGet(Model model, HttpServletRequest request){
        UsersDTORegisterRequest usersDTORegister =  UsersDTORegisterRequest.builder().build();
        model.addAttribute("usersDTORegister", usersDTORegister);
        return "register";
    }

    @PostMapping("/register")
    public String registerPost(Model model, @ModelAttribute("usersDTORegister") UsersDTORegisterRequest accountDTORegister) throws Exception{
        return userService.registerAccount(model, accountDTORegister);
    }


    @GetMapping("/forgot-password")
    public String forgotPasswordGET(Model model, HttpServletRequest request){
        UsersDTOForgotPasswordRequest usersDTOForgotPasswordRequest =  UsersDTOForgotPasswordRequest.builder().build();
        model.addAttribute("usersDTOForgotPasswordRequest", usersDTOForgotPasswordRequest);
        return "forgot-password";
    }
    
    @PostMapping("/forgot-password")
    public String forgotPasswordPost(Model model, @ModelAttribute("usersDTOForgotPasswordRequest") UsersDTOForgotPasswordRequest usersDTOForgotPasswordRequest) throws Exception{
        return userService.forgotPassword(model, usersDTOForgotPasswordRequest);
    }


}
