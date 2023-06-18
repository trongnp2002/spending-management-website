package com.group6.moneymanagementbooking.service;
import java.util.List;

import com.group6.moneymanagementbooking.enity.Income;

public interface IncomeService {
    public Income addIncome(Income income);

    public List<Income> findAll();  

}
