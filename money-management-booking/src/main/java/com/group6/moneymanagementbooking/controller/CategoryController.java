package com.group6.moneymanagementbooking.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.group6.moneymanagementbooking.enity.Accounts;
import com.group6.moneymanagementbooking.enity.Category;
import com.group6.moneymanagementbooking.service.CategoryService;
import com.group6.moneymanagementbooking.util.PaginationUtil;

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
    public String index(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int pageSize,
            Model model) {
        model.addAttribute("listcategory", categoryService.findAll());
        model.addAttribute("record", categoryService.findAll().size());
        Pageable pageable = PaginationUtil.getPageable(page, pageSize);
        List<Category> items = categoryService.findAll();
        Page<Category> itemsPage = PaginationUtil.paginate(pageable, items);
        model.addAttribute("page", itemsPage);
        return "list-category";
    }

    @GetMapping("/detail-category/{id}")
    public String detail(@PathVariable("id") int id, Model model) {
        model.addAttribute("category", categoryService.getCategory(id));
        return "detail-category";
    }

    @PostMapping("/detail-category")
    public String detail(@ModelAttribute Category category) {
        return Optional.ofNullable(categoryService.updateCategory(category)).map(t -> "redirect:/list-category")
                .orElse("failed");
    }

    @GetMapping("/delete-category/{id}")
    public String delete(@PathVariable("id") int id) {
        categoryService.deleteCategoryById(id);
        return "redirect:/list-category";
    }

    @GetMapping("/chart-category")
    public String chartCategory(Model model){
        model.addAttribute("categoryData", categoryService.getCategoryTotalExpenses(categoryService.findExpenseInCategory()));
        return "chart-category";
    }

}
