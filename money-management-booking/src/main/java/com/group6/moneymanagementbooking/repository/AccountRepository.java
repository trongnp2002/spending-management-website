package com.group6.moneymanagementbooking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group6.moneymanagementbooking.enity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{
    public Optional<Account> findByEmail(String email);
    
}
