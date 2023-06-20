package com.group6.moneymanagementbooking.service;

import java.util.List;

import com.group6.moneymanagementbooking.enity.Accounts;



public interface AccountsService {
    public Accounts addAccounts(Accounts accounts);
    
    public List<Accounts> findAll();

    public void updateActiveById(boolean action, int id);

    public void addBalance(double balance,int id);

    public void expenseBalance(double balance,int id);

}
