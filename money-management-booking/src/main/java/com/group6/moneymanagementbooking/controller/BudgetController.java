package com.group6.moneymanagementbooking.controller;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.group6.moneymanagementbooking.enity.Category;
import com.group6.moneymanagementbooking.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class BudgetController {
    @Autowired
    private final CategoryService categoryService;

    @GetMapping("/list-budget")
    public String listBudget(Model model) {
        model.addAttribute("listbudget", categoryService.findExpenseInCategory());
        model.addAttribute("record", categoryService.findExpenseInCategory().size());
        Collection<Category> categories = categoryService.findExpenseInCategory();
        Map<String,Double> categoryExpensesMap = categoryService.getCategoryTotalExpenses(categories);
        Map<String,Integer> expenseCountMap = categoryService.getExpenseCount(categories);
        model.addAttribute("categoryData", categoryService.getCategoryTotalExpenses(categoryService.findExpenseInCategory()));
        model.addAttribute("expenseCountMap", expenseCountMap);
        model.addAttribute("categoryExpensesMap", categoryExpensesMap);
        return "list-budget";
    }

    @GetMapping("/add-budget/{name}")
    public String addBudget(@PathVariable("name") String name, Model model) {
        model.addAttribute("category", categoryService.findByName(name));
        return "add-budget";
    }

    @PostMapping("/add-budget")
    public String addBudget(@ModelAttribute Category category) {
        categoryService.updateCategory(category.getBudget(), category.getName());
        return "redirect:/users/list-budget";
    }

}
