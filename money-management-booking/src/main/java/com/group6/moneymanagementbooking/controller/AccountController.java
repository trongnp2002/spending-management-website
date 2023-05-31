package com.group6.moneymanagementbooking.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group6.moneymanagementbooking.exception.custom.CustomBadRequestException;
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
   

    @PostMapping("/account/login")
    public AccountDTOResponse loginAccount(@RequestBody AccountDTOLoginRequest accountDTOLoginRequest ) throws CustomBadRequestException{
       return accountService.loginAccount(accountDTOLoginRequest);
    }
   
    
     @PostMapping("/account/register")
     public AccountDTOResponse registerAccount(@RequestBody AccountDTORegister accountDTORegister ){
        return accountService.registerAccount(accountDTORegister);
     }











}
