package com.group6.moneymanagementbooking.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.group6.moneymanagementbooking.enity.Category;
import com.group6.moneymanagementbooking.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class CategoryController {
    @Autowired
    private final CategoryService categoryService;

    @GetMapping("/add-category")
    public String addCategory(Model model) {
        model.addAttribute("category", new Category());
        return "add-category";
    }

    @PostMapping("/add-category")
    public String addCategory(@ModelAttribute Category category) {
        return Optional.ofNullable(categoryService.addCategory(category)).map(t -> "success").orElse("failed");
    }

    @GetMapping("/list-category")
    public String index(Model model) {
        model.addAttribute("listcategory", categoryService.findAll());
        return "list-category";
    }

    @GetMapping("/detail-category/{id}")
    public String detail(@PathVariable("id") int id, Model model) {
        model.addAttribute("category", categoryService.getCategory(id));
        return "detail-category";
    }

    @PostMapping("/detail-category")
    public String detail(@ModelAttribute Category category) {
        categoryService.addCategory(category);
        return Optional.ofNullable(categoryService.addCategory(category)).map(t -> "redirect:/list-category").orElse("failed");
    }

    @GetMapping("/delete-category/{id}")
    public String delete(@PathVariable("id") int id) {
        categoryService.deleteCategoryById(id);
        return "redirect:/list-category";
    }



}
