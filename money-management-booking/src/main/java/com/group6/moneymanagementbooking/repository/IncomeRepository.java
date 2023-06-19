package com.group6.moneymanagementbooking.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.group6.moneymanagementbooking.enity.Income;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Integer> {

    
}
