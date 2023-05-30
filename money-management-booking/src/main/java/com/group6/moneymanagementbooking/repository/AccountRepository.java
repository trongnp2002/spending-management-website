package com.group6.moneymanagementbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group6.moneymanagementbooking.enity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{
    
}
