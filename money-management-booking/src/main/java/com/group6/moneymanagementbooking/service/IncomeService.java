package com.group6.moneymanagementbooking.service;

import java.util.List;
import java.util.Optional;

import com.group6.moneymanagementbooking.enity.Income;

public interface IncomeService {
    public Income addIncome(Income income);

    public List<Income> findAll();

    public Optional<Income> getIncome(int id);

    public void deleteById(int id);

    public Income updateIncome(Income income);
}
