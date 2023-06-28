package com.group6.moneymanagementbooking.service.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.group6.moneymanagementbooking.enity.Income;
import com.group6.moneymanagementbooking.repository.AccountsRepository;
import com.group6.moneymanagementbooking.repository.IncomeRepository;
import com.group6.moneymanagementbooking.repository.UsersRepository;
import com.group6.moneymanagementbooking.service.AccountsService;
import com.group6.moneymanagementbooking.service.IncomeService;
import com.group6.moneymanagementbooking.util.SecurityUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IncomeServiceImpl implements IncomeService {
    @Autowired
    private final IncomeRepository incomeRepository;
    private final AccountsRepository accountsRepository;
    private final AccountsService accountsService;
    private final UsersRepository usersRepository;

    @Override
    public Income addIncome(Income income) {
        try {
            income.setUserId(usersRepository.findByEmail(SecurityUtils.getCurrentUsername()).get().getId());
            double amountIcome = income.getAmount();
            double balanceAccount = accountsRepository.findById(income.getAccounts().getId()).get().getBalance();
            if (income.getAmount() < 0)
                throw new Exception("Amount must be > 0");
            accountsRepository.addBalanceById(amountIcome + balanceAccount, income.getAccounts().getId());
            return incomeRepository.save(income);
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public List<Income> findAll() {
        List<Income> incomeList = incomeRepository
                .findAllByUserId(usersRepository.findByEmail(SecurityUtils.getCurrentUsername()).get().getId());
        // Sắp xếp danh sách theo trường ngày
        Collections.sort(incomeList, new Comparator<Income>() {
            @Override
            public int compare(Income income1, Income income2) {
                return Long.compare(income2.getId(), income1.getId());
            }
        });
        return incomeList;
    }

    @Override
    public Optional<Income> getIncome(int id) {
        return incomeRepository.findById(id);
    }

    @Override
    public void deleteById(int id) {
        Optional<Income> income = incomeRepository.findById(id);
        accountsService.expenseBalance(income.get().getAmount(), income.get().getAccounts().getId());
        incomeRepository.deleteById(id);
    }

    @Override
    public Income updateIncome(Income income) {
        try {
            income.setUserId(usersRepository.findByEmail(SecurityUtils.getCurrentUsername()).get().getId());
            double updateMoney = income.getAmount();
            double currentMoney = getIncome(income.getId()).get().getAmount();
            if (income.getAmount() < 0)
                throw new Exception("Amount must be >= 0");
            if (income.getAccounts().getId() == getIncome(income.getId()).get().getAccounts().getId()) {
                accountsService.addBalance(updateMoney - currentMoney, income.getAccounts().getId());
                return incomeRepository.save(income);
            }
            accountsService.addBalance(-currentMoney, getIncome(income.getId()).get().getAccounts().getId());
            accountsService.addBalance(updateMoney, income.getAccounts().getId());
            return incomeRepository.save(income);
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

}
