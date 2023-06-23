package com.group6.moneymanagementbooking.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.group6.moneymanagementbooking.enity.Income;
import com.group6.moneymanagementbooking.repository.AccountsRepository;
import com.group6.moneymanagementbooking.repository.IncomeRepository;
import com.group6.moneymanagementbooking.service.AccountsService;
import com.group6.moneymanagementbooking.service.IncomeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IncomeServiceImpl implements IncomeService {
    @Autowired
    private final IncomeRepository incomeRepository;
    private final AccountsRepository accountsRepository;
    private final AccountsService accountsService;
    @Override
    public Income addIncome(Income income) {
        try{
        double amountIcome = income.getAmount();
        double balanceAccount = accountsRepository.findById(income.getAccounts().getId()).get().getBalance();
        if (income.getAmount()<0) throw new Exception("Amount must be > 0");
        accountsRepository.addBalanceById(amountIcome+balanceAccount, income.getAccounts().getId());
        return incomeRepository.save(income);
        }catch(Exception e){
            e.getMessage();
        }
        return null;
    }

    @Override
    public List<Income> findAll() {
     return incomeRepository.findAll();
    }

    @Override
    public Optional<Income> getIncome(int id) {
        return incomeRepository.findById(id);
    }

    @Override
    public void deleteById(int id) {
        incomeRepository.deleteById(id);
    }

    @Override
    public Income updateIncome(Income income) {
        try{
        double updateMoney = income.getAmount();
        double currentMoney = getIncome(income.getId()).get().getAmount();
        if (income.getAmount()<0) throw new Exception("Amount must be >= 0");
        accountsService.addBalance(updateMoney-currentMoney,income.getAccounts().getId());
        return incomeRepository.save(income); 
        }catch (Exception e){
           e.getMessage();    
        }
        return null;
    }
    }   
    

