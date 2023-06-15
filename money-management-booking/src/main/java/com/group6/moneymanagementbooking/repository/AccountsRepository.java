package com.group6.moneymanagementbooking.repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group6.moneymanagementbooking.enity.Accounts;



@Repository
public interface AccountsRepository extends JpaRepository<Accounts,Integer> {

    public List<Accounts> findAll();

    public Optional<Accounts> findByName(String name);

   
}
