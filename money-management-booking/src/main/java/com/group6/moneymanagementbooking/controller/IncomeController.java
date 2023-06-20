package com.group6.moneymanagementbooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
        accountsService.addBalance(income.getAmount(),income.getAccounts().getId());
        return "redirect:/list-income";
    }

    @GetMapping("/list-income")
    public String index(Model model){
        model.addAttribute("listincome", incomeService.findAll());
        return "list-income";
    }

    @GetMapping("/detail-income/{id}")
    public String detail(@PathVariable("id") int id, Model model){
        model.addAttribute("income", incomeService.getIncome(id));
        model.addAttribute("listaccount", accountsService.findAll());
        model.addAttribute("listcategory", categoryService.findIncomeInCategory());
        return "detail-income";
    }

    @PostMapping("/detail-income")
    public String detail(@ModelAttribute Income income){
        double updateMoney = income.getAmount();
        double currentMoney = incomeService.getIncome(income.getId()).get().getAmount();
        incomeService.addIncome(income);
        accountsService.addBalance(updateMoney-currentMoney,income.getAccounts().getId());
        return "redirect:/list-income";
    }

    @GetMapping("/delete-income/{id}")
    public String delete(@PathVariable("id") int id){
        incomeService.deleteById(id);
        return "redirect:/list-income";
    }
}
