package com.group6.moneymanagementbooking.service;
import java.util.List;
import java.util.Optional;

import com.group6.moneymanagementbooking.enity.Expenses;

public interface ExpensesService {
    public Expenses addExpenses(Expenses expenses);

    public List<Expenses> findAll(); 

    public Optional<Expenses> getExpense(int id);

    public void deleteById(int id);
}
