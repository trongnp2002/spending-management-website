package com.group6.moneymanagementbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group6.moneymanagementbooking.enity.Expenses;
@Repository
public interface ExpensesRepository extends JpaRepository<Expenses,Integer> {
    
}
