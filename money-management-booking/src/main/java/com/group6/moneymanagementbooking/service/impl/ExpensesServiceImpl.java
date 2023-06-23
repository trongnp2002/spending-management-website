package com.group6.moneymanagementbooking.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group6.moneymanagementbooking.enity.Expenses;
import com.group6.moneymanagementbooking.repository.AccountsRepository;
import com.group6.moneymanagementbooking.repository.ExpensesRepository;
import com.group6.moneymanagementbooking.service.AccountsService;
import com.group6.moneymanagementbooking.service.ExpensesService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExpensesServiceImpl implements ExpensesService {

    @Autowired
    private final ExpensesRepository expensesRepository;
    private final AccountsRepository accountsRepository;
    private final AccountsService accountsService;


    @Override
    public Expenses addExpenses(Expenses expenses) {
        try {
            double amountExpense = expenses.getAmount();
            double balanceAccount = accountsRepository.findById(expenses.getAccounts().getId()).get().getBalance();
            if (amountExpense == 0)
                throw new Exception("Amount can't not empty");
            if (amountExpense < 0)
                throw new Exception("Amount must be > 0");
            if (balanceAccount-amountExpense<0)
                System.out.println("Warning Balance <0");
            accountsRepository.addBalanceById(balanceAccount - amountExpense, expenses.getAccounts().getId());
            return expensesRepository.save(expenses);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
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
        Optional<Expenses> expenses = expensesRepository.findById(id);
        accountsService.addBalance(expenses.get().getAmount(), expenses.get().getAccounts().getId());
        expensesRepository.deleteById(id);
    }

    @Override
    public Expenses updateExpenses(Expenses expenses) {
        try {
            double updateMoney = expenses.getAmount();
            double currentMoney = getExpense(expenses.getId()).get().getAmount();
            double balanceAccount = accountsRepository.findById(expenses.getAccounts().getId()).get().getBalance();
            if (expenses.getAmount() < 0)
                throw new Exception("Amount must be > 0");
            if (balanceAccount-updateMoney<0)
                System.out.println("Warning Balance <0");
            accountsService.expenseBalance(updateMoney-currentMoney,expenses.getAccounts().getId());
            return expensesRepository.save(expenses);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }

    }

}
