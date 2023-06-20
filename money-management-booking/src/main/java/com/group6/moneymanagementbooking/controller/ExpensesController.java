package com.group6.moneymanagementbooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.group6.moneymanagementbooking.enity.Expenses;
import com.group6.moneymanagementbooking.service.AccountsService;
import com.group6.moneymanagementbooking.service.CategoryService;
import com.group6.moneymanagementbooking.service.ExpensesService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ExpensesController {
    @Autowired
    private final ExpensesService expensesService;
    private final AccountsService accountsService;
    private final CategoryService categoryService;


    @GetMapping("/add-expenses")
    public String addExpense(Model model){
    model.addAttribute("listaccount", accountsService.findAll());
    model.addAttribute("listcategory", categoryService.findExpenseInCategory());
    model.addAttribute("expense", new Expenses());
    return "add-expenses";
    }

    @PostMapping("/add-expenses")
    public String addExpense(@ModelAttribute Expenses expense){
    expensesService.addExpenses(expense);
    accountsService.expenseBalance(expense.getAmount(),expense.getAccounts().getId());
    return "redirect:/list-expenses";
    }

    @GetMapping("/list-expenses")
    public String index(Model model){
    model.addAttribute("listexpenses", expensesService.findAll());
    return "list-expenses";
    }

    
    @GetMapping("/detail-expense/{id}")
    public String detail(@PathVariable("id") int id, Model model){
        model.addAttribute("expense", expensesService.getExpense(id));
        model.addAttribute("listaccount", accountsService.findAll());
        model.addAttribute("listcategory", categoryService.findExpenseInCategory());
        return "detail-expenses";
    }

    @PostMapping("/detail-expense")
    public String detail(@ModelAttribute Expenses expense){
        double updateMoney = expense.getAmount();
        double currentMoney = expensesService.getExpense(expense.getId()).get().getAmount();
        expensesService.addExpenses(expense);
        accountsService.expenseBalance(updateMoney-currentMoney,expense.getAccounts().getId());
        return "redirect:/list-expenses";
    }

    @GetMapping("/delete-expense/{id}")
    public String delete(@PathVariable("id") int id){
        expensesService.deleteById(id);
        return "redirect:/list-expenses";
    }
}
