package com.group6.moneymanagementbooking.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.group6.moneymanagementbooking.enity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    public Optional<Category> findByName(String name);

    @Query("select c from Category c where c.income_or_expense = 1")
    public List<Category> findIncomeInCategory();

    @Query("select c from Category c where c.income_or_expense = 0")
    public List<Category> findExpenseInCategory();
    @Modifying
    @Transactional
    @Query("UPDATE Category SET budget = ?1 WHERE name = ?2")
    public void updateCategory(double budget, String name);

}
