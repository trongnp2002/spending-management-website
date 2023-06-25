package com.group6.moneymanagementbooking.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.group6.moneymanagementbooking.enity.Category;
import com.group6.moneymanagementbooking.enity.Expenses;
import com.group6.moneymanagementbooking.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BudgetController {
    @Autowired
    private final CategoryService categoryService;

    @GetMapping("/list-budget")
    public String listCategory(Model model){
      model.addAttribute("listbudget", categoryService.findAll());
      return "list-budget";
    }

    @GetMapping("/add-budget/{name}")
    public String addBudget(@PathVariable("name") String name, Model model) {
        model.addAttribute("category", categoryService.findByName(name));
        return "add-budget";
    }

    @PostMapping("/add-budget")
    public String addBudget(@ModelAttribute Category category) {
       categoryService.updateCategory(category.getBudget(),category.getName());
        return "redirect:/list-budget";
    }


}
