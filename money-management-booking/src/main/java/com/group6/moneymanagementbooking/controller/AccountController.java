package com.group6.moneymanagementbooking.controller;

import java.util.Random;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group6.moneymanagementbooking.exception.custom.CustomBadRequestException;
import com.group6.moneymanagementbooking.model.Captcha;
import com.group6.moneymanagementbooking.model.account.dto.AccountDTOLoginRequest;
import com.group6.moneymanagementbooking.model.account.dto.AccountDTORegister;
import com.group6.moneymanagementbooking.model.account.dto.AccountDTOResponse;
import com.group6.moneymanagementbooking.service.AccountService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin
public class AccountController {
    private final AccountService accountService;
   private final Captcha captcha1;

    @PostMapping("/account/login")
    public AccountDTOResponse loginAccount(@RequestBody AccountDTOLoginRequest accountDTOLoginRequest ) throws CustomBadRequestException{
      String captcha = captcha1.getCaptchaCode(); 
       return accountService.loginAccount(accountDTOLoginRequest,captcha);
    }
   
    
     @PostMapping("/account/register")
     public AccountDTOResponse registerAccount(@RequestBody AccountDTORegister accountDTORegister ) throws CustomBadRequestException{
        return accountService.registerAccount(accountDTORegister);
     }



  







}
