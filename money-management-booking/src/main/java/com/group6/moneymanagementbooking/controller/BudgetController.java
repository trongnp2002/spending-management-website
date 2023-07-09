package com.group6.moneymanagementbooking.controller;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.group6.moneymanagementbooking.enity.Category;
import com.group6.moneymanagementbooking.service.CategoryService;
import com.group6.moneymanagementbooking.service.ExpensesService;
import com.group6.moneymanagementbooking.service.UsersService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class BudgetController {
    @Autowired
    private final CategoryService categoryService;
    private final UsersService usersService;
    private final ExpensesService expensesService;

    @GetMapping("/list-budget")
    public String listBudget(Model model) {
        model.addAttribute("listbudget", categoryService.findExpenseInCategory());
        model.addAttribute("record", categoryService.findExpenseInCategory().size());
        Collection<Category> categories = categoryService.findExpenseInCategory();
        Map<String, Double> categoryExpensesMap = categoryService.getCategoryTotalExpenses(categories);
        Map<String, Integer> expenseCountMap = categoryService.getExpenseCount(categories);
        model.addAttribute("categoryData",
                categoryService.getCategoryTotalExpenses(categoryService.findExpenseInCategory()));
        model.addAttribute("categoryDataBudget",
                categoryService.getCategoriesInfo(categoryService.findExpenseInCategory()));
        model.addAttribute("expenseCountMap", expenseCountMap);
        model.addAttribute("categoryExpensesMap", categoryExpensesMap);
        model.addAttribute("monthlySpending", usersService.getUsers().getMonthlySpending());
        model.addAttribute("totalExpenseByMonth", expensesService.getTotalAmountCurrentMonth());
        return "list-budget";
    }

    @PostMapping("/add-budget")
    public String addBudget(@ModelAttribute Category addcategory) {
        categoryService.updateCategory(addcategory.getBudget(), addcategory.getName());
        return "redirect:/users/list-budget";
    }

}
