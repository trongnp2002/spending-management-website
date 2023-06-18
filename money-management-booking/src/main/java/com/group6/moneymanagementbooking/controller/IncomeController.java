package com.group6.moneymanagementbooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.group6.moneymanagementbooking.enity.Income;
import com.group6.moneymanagementbooking.service.AccountsService;
import com.group6.moneymanagementbooking.service.CategoryService;
import com.group6.moneymanagementbooking.service.IncomeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class IncomeController {
    @Autowired
    private final IncomeService incomeService;
    private final AccountsService accountsService;
    private final CategoryService categoryService;

    @GetMapping("/add-income")
    public String addIncome(Model model){
    model.addAttribute("listaccount", accountsService.findAll());
    model.addAttribute("listcategory", categoryService.findIncomeInCategory());
    model.addAttribute("income", new Income());
    return "add-income";
    }

    @PostMapping("/add-income")
    public String addIncome(@ModelAttribute Income income){
        incomeService.addIncome(income);
        accountsService.addBalance(income.getAmount(),income.getAccountId());
        return "redirect:/list-income";
    }

    @GetMapping("/list-income")
    public String index(Model model){
        model.addAttribute("listincome", incomeService.findAll());
        model.addAttribute("listaccount", accountsService.findAll());
        model.addAttribute("listcategory", categoryService.findAll());
        return "list-income";
    }
}
