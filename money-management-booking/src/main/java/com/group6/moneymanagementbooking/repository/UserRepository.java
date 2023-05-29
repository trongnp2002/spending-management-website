package com.group6.moneymanagementbooking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group6.moneymanagementbooking.enity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

    public Optional<User> findByEmail(String email);
    
}
