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
        accounts.setUser_id(1);
        return accountsRepository.save(accounts);
    }

    @Override
    public List<Accounts> findAll() {
        return accountsRepository.findAll();
    }

    

}
