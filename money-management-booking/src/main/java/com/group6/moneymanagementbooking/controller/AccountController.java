package com.group6.moneymanagementbooking.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.group6.moneymanagementbooking.exception.custom.CustomBadRequestException;
import com.group6.moneymanagementbooking.model.account.dto.AccountDTOLoginRequest;
import com.group6.moneymanagementbooking.model.account.dto.AccountDTORegister;
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
        model.addAttribute("report", "");
        return "login";
    }
    @PostMapping("/accounts/login")
    public String postLoginFuntion(Model model, HttpServletRequest request, HttpServletResponse response, @ModelAttribute("accountDTOLoginRequest") AccountDTOLoginRequest accountDTOLoginRequest) throws CustomBadRequestException {
       return accountService.loginAccount(model, request,response,accountDTOLoginRequest);
       
    }
    @GetMapping("/accounts/home")
    public String goHome(HttpServletRequest request, HttpServletResponse response ) throws CustomBadRequestException {
        return "home";
        
     }
    @GetMapping("/accounts/register")
    public String goToRegister(Model model, HttpServletRequest request){
        AccountDTORegister accountDTORegister =  AccountDTORegister.builder().build();
        model.addAttribute("accountDTORegister", accountDTORegister);
        return "register";
    }
    @PostMapping("/accounts/register")
    public String register(Model model, @ModelAttribute("accountDTORegister") AccountDTORegister accountDTORegister) throws Exception{
        return accountService.registerAccount(model, accountDTORegister);
    }


    @GetMapping("/accounts/check/email")
    @ResponseStatus(value = HttpStatus.OK)
    public void checkEmail(HttpServletRequest request, HttpServletResponse response) throws Exception{
        accountService.checkEmail(request, response);

    }
    @GetMapping("/accounts/check/phone")
    @ResponseStatus(value = HttpStatus.OK)
    public void checkPhone(HttpServletRequest request, HttpServletResponse response) throws Exception{
        accountService.checkPhone(request, response);

    }
}
