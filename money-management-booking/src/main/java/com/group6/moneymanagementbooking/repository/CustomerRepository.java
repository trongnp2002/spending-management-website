package com.group6.moneymanagementbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group6.moneymanagementbooking.enity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

    
    
}
