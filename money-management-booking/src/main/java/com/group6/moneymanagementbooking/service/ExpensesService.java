package com.group6.moneymanagementbooking.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.group6.moneymanagementbooking.enity.Expenses;

public interface ExpensesService {
    public Expenses addExpenses(Expenses expenses);

    public List<Expenses> findAll();

    public Optional<Expenses> getExpense(int id);

    public void deleteById(int id);

    public Expenses updateExpenses(Expenses expenses);

    public List<Expenses> getExpensesByMonth(int month, int year);

    public double totalExpenseInMonth();

    public int getCountByTitle(String name);

    public Map<String, Double> calculateMonthlyExpenses(List<Expenses> expenses);
}
