package com.group6.moneymanagementbooking.service.impl;


import java.util.List;
import java.util.Optional;

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
        try{
        boolean checkAccountExits = accountsRepository.findByName(accounts.getName()).isPresent();
        if(checkAccountExits) throw new Exception("Account Name Have Exists");
        if(accounts.getBalance()<0) throw new Exception("Balance Mush Be >=0");
        if(accounts.getName().isEmpty()) throw new Exception("Name Can't Not Null");
        accounts.setUser_id(1); 
        return accountsRepository.save(accounts);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
       return null;
    }
    @Override
    public List<Accounts> findAll() {
        return  accountsRepository.findAll();
    }
    @Override
    public void updateActiveById(boolean action, int id) {
        accountsRepository.updateActiveById(action, id);
    }
    @Override
    public void addBalance(double balance,int id) {
        accountsRepository.addBalanceById(balance+accountsRepository.findBalanceById(id),id);    
    }
    
    @Override
    public void expenseBalance(double balance, int id) {
        accountsRepository.expenseBalanceById(accountsRepository.findBalanceById(id)-balance,id);
    }
    @Override
    public Optional<Accounts> findById(int id) {
        return accountsRepository.findById(id);
    }
    @Override
    public List<Accounts> findByActive() {
        return accountsRepository.findByActive(true);
    }
    @Override
    public Accounts updateAccount(Accounts accounts) {
        accounts.setUser_id(1);
        return accountsRepository.save(accounts);
    }
    @Override
    public void deleteById(int id) {
        accountsRepository.deleteById(id);
    }
}
