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

import com.group6.moneymanagementbooking.enity.Expenses;
import com.group6.moneymanagementbooking.enity.Income;
import com.group6.moneymanagementbooking.service.AccountsService;
import com.group6.moneymanagementbooking.service.CategoryService;
import com.group6.moneymanagementbooking.service.IncomeService;
import com.group6.moneymanagementbooking.util.PaginationUtil;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class IncomeController {
    @Autowired
    private final IncomeService incomeService;
    private final AccountsService accountsService;
    private final CategoryService categoryService;

    @GetMapping("/add-income")
    public String addIncome(Model model) {
        model.addAttribute("listaccount", accountsService.findByActive());
        model.addAttribute("listcategory", categoryService.findIncomeInCategory());
        model.addAttribute("income", new Income());
        return "add-income";
    }

    @PostMapping("/add-income")
    public String addIncome(@ModelAttribute Income income) {
        return Optional.ofNullable(incomeService.addIncome(income)).map(t -> "redirect:/list-income").orElse("failed");
    }

    @GetMapping("/list-income")
    public String index(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int pageSize,
            Model model) {
        model.addAttribute("listincome", incomeService.findAll());
        model.addAttribute("record", incomeService.findAll().size());
        Pageable pageable = PaginationUtil.getPageable(page, pageSize);
        List<Income> items = incomeService.findAll();
        Page<Income> itemsPage = PaginationUtil.paginate(pageable, items);
        model.addAttribute("page", itemsPage);
        return "list-income";
    }

    @GetMapping("/detail-income/{id}")
    public String detail(@PathVariable("id") int id, Model model) {
        model.addAttribute("income", incomeService.getIncome(id));
        model.addAttribute("listaccount", accountsService.findByActive());
        model.addAttribute("listcategory", categoryService.findIncomeInCategory());
        return "detail-income";
    }

    @PostMapping("/detail-income")
    public String detail(@ModelAttribute Income income) {
        return Optional.ofNullable(incomeService.updateIncome(income)).map(t -> "redirect:/list-income")
                .orElse("failed");
    }

    @GetMapping("/delete-income/{id}")
    public String delete(@PathVariable("id") int id) {
        incomeService.deleteById(id);
        return "redirect:/users/list-income";
    }
}
