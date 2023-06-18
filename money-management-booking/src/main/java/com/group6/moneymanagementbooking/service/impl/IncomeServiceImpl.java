package com.group6.moneymanagementbooking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group6.moneymanagementbooking.enity.Income;
import com.group6.moneymanagementbooking.repository.IncomeRepository;
import com.group6.moneymanagementbooking.service.IncomeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IncomeServiceImpl implements IncomeService {
    @Autowired
    private final IncomeRepository incomeRepository;

    @Override
    public Income addIncome(Income income) {
        return incomeRepository.save(income);
    }

    @Override
    public List<Income> findAll() {
        return incomeRepository.findAll();
    }

    
}
