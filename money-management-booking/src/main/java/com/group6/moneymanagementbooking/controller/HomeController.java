package com.group6.moneymanagementbooking.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.group6.moneymanagementbooking.model.exception.custom.CustomBadRequestException;




public class HomeController {
        @GetMapping("/homepage")
    public String homepag(Model model) throws CustomBadRequestException {
      //  AccountDTOLoginRequest accountDTOLoginRequest =  AccountDTOLoginRequest.builder().build();
     //   model.addAttribute("accountDTOLoginRequest", accountDTOLoginRequest);
        return "home";
    }

    
    @GetMapping("/homepage/dashboard")
    public String dashboard(Model model) throws CustomBadRequestException {
      //  AccountDTOLoginRequest accountDTOLoginRequest =  AccountDTOLoginRequest.builder().build();
     //   model.addAttribute("accountDTOLoginRequest", accountDTOLoginRequest);
        return "dashboard";
    }

  
}
