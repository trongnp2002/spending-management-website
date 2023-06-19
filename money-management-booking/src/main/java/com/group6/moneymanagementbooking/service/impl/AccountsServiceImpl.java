package com.group6.moneymanagementbooking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group6.moneymanagementbooking.enity.Accounts;
import com.group6.moneymanagementbooking.repository.AccountsRepository;
import com.group6.moneymanagementbooking.service.AccountsService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountsServiceImpl implements AccountsService {
    @Autowired
    private final AccountsRepository accountsRepository;

    @Override
    public Accounts addAccounts(Accounts accounts) {
        accounts.setUserId(1);
        return accountsRepository.save(accounts);
    }

    @Override
    public List<Accounts> findAll() {
        return accountsRepository.findAll();
    }

    @Override
    public void updateActiveById(boolean action, int id) {
        accountsRepository.updateActiveById(action, id);
    }

    @Override
    public List<Accounts> findAllByUserId(int userId) {
        return accountsRepository.findAllByUserId(userId);
    }

    @Override
    public Accounts findById(int id) {
        return (accountsRepository.findById(id)).get();
    }

}
