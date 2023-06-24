package com.group6.moneymanagementbooking.controller;
import java.util.Optional;

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
    public String addExpense(Model model) {
        model.addAttribute("listaccount", accountsService.findByActive());
        model.addAttribute("listcategory", categoryService.findExpenseInCategory());
        model.addAttribute("expense", new Expenses());
        return "add-expenses";
    }

    @PostMapping("/add-expenses")
    public String addExpense(@ModelAttribute Expenses expense) {
        return Optional.ofNullable(expensesService.addExpenses(expense)).map(t -> "redirect:/list-expenses")
                .orElse("failed");
    }

    @GetMapping("/list-expenses")
    public String index(Model model) {
        model.addAttribute("listexpenses", expensesService.findAll());
        return "list-expenses";
    }

    @GetMapping("/detail-expense/{id}")
    public String detail(@PathVariable("id") int id, Model model) {
        model.addAttribute("expense", expensesService.getExpense(id));
        model.addAttribute("listaccount", accountsService.findByActive());
        model.addAttribute("listcategory", categoryService.findExpenseInCategory());
        return "detail-expenses";
    }

    @PostMapping("/detail-expense")
    public String detail(@ModelAttribute Expenses expense) {
        return Optional.ofNullable(expensesService.updateExpenses(expense)).map(t -> "redirect:/list-expenses")
                .orElse("failed");
    }

    @GetMapping("/delete-expense/{id}")
    public String delete(@PathVariable("id") int id) {
        expensesService.deleteById(id);
        return "redirect:/list-expenses";
    }

    // @GetMapping("/expenses-month")
    // public String totalExpensesInMonth(Model model){
    //    model.addAttribute("total", expensesService.totalExpenseInMonth());
    //    return "total-expense-month";
    // }

}
