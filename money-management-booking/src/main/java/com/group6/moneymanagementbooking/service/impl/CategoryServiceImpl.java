package com.group6.moneymanagementbooking.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.group6.moneymanagementbooking.enity.Category;
import com.group6.moneymanagementbooking.enity.Expenses;
import com.group6.moneymanagementbooking.repository.CategoryRepository;
import com.group6.moneymanagementbooking.repository.UsersRepository;
import com.group6.moneymanagementbooking.service.CategoryService;
import com.group6.moneymanagementbooking.util.SecurityUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private final CategoryRepository categoryRepository;
    private final UsersRepository usersRepository;

    @Override
    public Category addCategory(Category category) {
        try {
            category.setUser_id(usersRepository.findByEmail(SecurityUtils.getCurrentUsername()).get().getId());
            boolean category1 = categoryRepository.findByNameAndUser_id(category.getName(), category.getUser_id())
                    .isPresent();
            if (category1)
                throw new Exception("Name does exists");
            if (category.getName().isEmpty())
                throw new Exception("Name Can't Not Null");
            return categoryRepository.save(category);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository
                .findAllByUserId(usersRepository.findByEmail(SecurityUtils.getCurrentUsername()).get().getId());
    }

    @Override
    public Optional<Category> getCategory(int id) {
        return categoryRepository.findById(id);
    }

    @Override
    public void deleteCategoryById(int id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<Category> findIncomeInCategory() {
        return categoryRepository
                .findIncomeInCategory(usersRepository.findByEmail(SecurityUtils.getCurrentUsername()).get().getId());
    }

    @Override
    public List<Category> findExpenseInCategory() {
        return categoryRepository
                .findExpenseInCategory(usersRepository.findByEmail(SecurityUtils.getCurrentUsername()).get().getId());
    }

    @Override
    public Optional<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public void updateCategory(double budget, String name) {
        categoryRepository.updateCategory(budget, name);
    }

    @Override
    public Category updateCategory(Category category) {
        category.setUser_id(usersRepository.findByEmail(SecurityUtils.getCurrentUsername()).get().getId());
        return categoryRepository.save(category);
    }

    @Override
    public double calculateTotalExpenses(Category category) {
        double totalExpenses = 0;
        if (category.getExpenses() != null) {
            for (Expenses expense : category.getExpenses()) {
                totalExpenses += expense.getAmount();
            }
        }
        return totalExpenses;
    }

    @Override
    public Map<String, Double> getCategoryTotalExpenses(Collection<Category> categories) {
        Map<String, Double> categoryExpensesMap = new HashMap<>();

        for (Category category : categories) {
            double totalExpense = 0.0;
            for (Expenses expense : category.getExpenses()) {
                totalExpense += expense.getAmount();
            }
            categoryExpensesMap.put(category.getName(), totalExpense);
        }

        return categoryExpensesMap;
    }

    @Override
    public Map<String, Integer> getExpenseCount(Collection<Category> categories) {
        Map<String, Integer> expenseCountMap = new HashMap<>();
        for (Category category : categories) {
            int expenseCount = category.getExpenses().size();
            expenseCountMap.put(category.getName(), expenseCount);
        }
        return expenseCountMap;
    }

}
