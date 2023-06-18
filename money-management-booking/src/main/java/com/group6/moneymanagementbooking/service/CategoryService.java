package com.group6.moneymanagementbooking.service;
import java.util.List;
import java.util.Optional;

import com.group6.moneymanagementbooking.enity.Category;

public interface CategoryService {
    public Category addCategory(Category category);

    public List<Category> findAll();

    public Optional<Category> getCategory(int id);

    public void deleteCategoryById(int id);

    public List<Category> findIncomeInCategory();
}
