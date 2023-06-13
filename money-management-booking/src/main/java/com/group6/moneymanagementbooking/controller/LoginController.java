package com.group6.moneymanagementbooking.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


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
    public String getLoginFunction(Model model) throws CustomBadRequestException {
        UsersDTOLoginRequest accountDTOLoginRequest =  UsersDTOLoginRequest.builder().build();
        model.addAttribute("accountDTOLoginRequest", accountDTOLoginRequest);
        model.addAttribute("report", "");
        return "login";
    }


    @GetMapping("/register")
    public String goToRegister(Model model, HttpServletRequest request){
        UsersDTORegisterRequest accountDTORegister =  UsersDTORegisterRequest.builder().build();
        model.addAttribute("accountDTORegister", accountDTORegister);
        return "register";
    }
    @PostMapping("/register")
    public String register(Model model, @ModelAttribute("accountDTORegister") UsersDTORegisterRequest accountDTORegister) throws Exception{
        return userService.registerAccount(model, accountDTORegister);
    }



}
