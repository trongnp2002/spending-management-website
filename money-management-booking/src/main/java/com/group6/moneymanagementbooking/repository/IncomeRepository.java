package com.group6.moneymanagementbooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.group6.moneymanagementbooking.enity.Income;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Integer> {

    
}
