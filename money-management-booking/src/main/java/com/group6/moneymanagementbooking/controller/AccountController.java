package com.group6.moneymanagementbooking.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.group6.moneymanagementbooking.exception.custom.CustomBadRequestException;
import com.group6.moneymanagementbooking.model.account.dto.AccountDTOLoginRequest;

import com.group6.moneymanagementbooking.service.AccountService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin
public class AccountController {
    private final AccountService accountService;
    
    @GetMapping("/accounts/login")
    public String getLoginFunction(Model model) throws CustomBadRequestException {
        AccountDTOLoginRequest accountDTOLoginRequest =  AccountDTOLoginRequest.builder().build();
        model.addAttribute("accountDTOLoginRequest", accountDTOLoginRequest);
        return "login";
    }
    @PostMapping("/accounts/login")
    public String postLoginFuntion(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("accountDTOLoginRequest") AccountDTOLoginRequest accountDTOLoginRequest) throws CustomBadRequestException {
       return accountService.loginAccount(request,response,accountDTOLoginRequest);
       
    }
    @GetMapping("/accounts/home")
    public String goHome(HttpServletRequest request, HttpServletResponse response ) throws CustomBadRequestException {
        return "home";
        
     }
    @GetMapping("/accounts/register")
    public String goToIndex(Model model, HttpServletRequest request){


        return "register";
    }

 
}
