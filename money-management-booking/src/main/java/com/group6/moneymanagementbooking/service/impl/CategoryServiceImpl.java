package com.group6.moneymanagementbooking.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.group6.moneymanagementbooking.enity.Category;
import com.group6.moneymanagementbooking.repository.CategoryRepository;
import com.group6.moneymanagementbooking.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private final CategoryRepository categoryRepository;

    @Override
    public Category addCategory(Category category) {
     try{
        boolean category1 = categoryRepository.findByName(category.getName()).isPresent();
        if(category1) throw new Exception("Category Name Have Exists");
        if(category.getName().isEmpty()) throw new Exception("Name Can't Not Null");
        category.setUser_id(1); 
        return categoryRepository.save(category);
        }catch(Exception e){
        System.out.println(e.getMessage());
        }
       return null;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
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
        return categoryRepository.findIncomeInCategory();
    }

    @Override
    public List<Category> findExpenseInCategory() {
       return categoryRepository.findExpenseInCategory();
    }

    @Override
    public Optional<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }
}
