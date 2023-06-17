package com.group6.moneymanagementbooking.controller;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.group6.moneymanagementbooking.enity.Accounts;
import com.group6.moneymanagementbooking.service.AccountsService;

import lombok.RequiredArgsConstructor;



@Controller
@RequiredArgsConstructor
public class AccountController {
    @Autowired
    private final AccountsService accountsService;

    @GetMapping("/add-account")
    public String addAccount(Model model){
        model.addAttribute("accounts",new Accounts());
        return "addaccount";
    }
    @PostMapping("/add-account")
    public String addAccount(@ModelAttribute Accounts accounts){
        return Optional.ofNullable(accountsService.addAccounts(accounts)).map(t -> "success").orElse("failed");
    }
    
    @GetMapping("/list-account")
    public String index(Model model){
        model.addAttribute("listaccount", accountsService.findAll());
        return "listaccount";
    }

    @GetMapping("/list-account/{id}/{action}")
    public String updateActive(@PathVariable("id") int id,@PathVariable("action") Boolean action, Model model){
    accountsService.updateActiveById(action, id);
    model.addAttribute("listaccount", accountsService.findAll());
    return "listaccount";
    }
}
