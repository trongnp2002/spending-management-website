package com.group6.moneymanagementbooking.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group6.moneymanagementbooking.enity.Expenses;
import com.group6.moneymanagementbooking.repository.ExpensesRepository;
import com.group6.moneymanagementbooking.service.ExpensesService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExpensesServiceImpl implements ExpensesService {

    @Autowired
    private final ExpensesRepository expensesRepository;

    @Override
    public Expenses addExpenses(Expenses expenses) {
        return expensesRepository.save(expenses);
    }

    @Override
    public List<Expenses> findAll() {
        return expensesRepository.findAll();
    }

    @Override
    public Optional<Expenses> getExpense(int id) {
        return expensesRepository.findById(id);
    }

    @Override
    public void deleteById(int id) {
        expensesRepository.deleteById(id);
    }
    
}
