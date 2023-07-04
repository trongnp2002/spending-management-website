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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.group6.moneymanagementbooking.enity.Category;
import com.group6.moneymanagementbooking.service.CategoryService;
import com.group6.moneymanagementbooking.util.PaginationUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/users")
public class CategoryController {
    @Autowired
    private final CategoryService categoryService;

    @PostMapping("/add-category")
    public String addCategory(@ModelAttribute Category category, RedirectAttributes redirectAttributes) {
        categoryService.addCategory(category, redirectAttributes);
        return "redirect:/users/list-category";
    }

    @GetMapping("/list-category")
    public String index(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int pageSize,
            Model model, @ModelAttribute("mess") String mess) {
        model.addAttribute("mess", mess);
        model.addAttribute("listcategory", categoryService.findAll());
        model.addAttribute("record", categoryService.findAll().size());
        model.addAttribute("addcategory", new Category());
        Pageable pageable = PaginationUtil.getPageable(page, pageSize);
        List<Category> items = categoryService.findAll();
        Page<Category> itemsPage = PaginationUtil.paginate(pageable, items);
        model.addAttribute("page", itemsPage);
        return "list-category";
    }

    @PostMapping("/detail-category")
    public String detail(@ModelAttribute Category category) {
        return Optional.ofNullable(categoryService.updateCategory(category)).map(t -> "redirect:/users/list-category")
                .orElse("failed");
    }

    @GetMapping("/delete-category/{id}")
    public String delete(@PathVariable("id") int id) {
        categoryService.deleteCategoryById(id);
        return "redirect:/users/list-category";
    }

    @GetMapping("/chart-category")
    public String chartCategory(Model model) {
        model.addAttribute("categoryData",
                categoryService.getCategoryTotalExpenses(categoryService.findExpenseInCategory()));
        return "chart-category";
    }

}
