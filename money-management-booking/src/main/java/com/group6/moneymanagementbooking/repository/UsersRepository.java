package com.group6.moneymanagementbooking.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group6.moneymanagementbooking.dto.request.UserDTOEditProfileRequest;
import com.group6.moneymanagementbooking.enity.Users;


@Repository
public interface UsersRepository extends JpaRepository<Users, Integer>{

    public Optional<Users> findByEmail(String email);

    public Optional<Users> findByPhone(String phone);
    //public Optional<UserDTOEditProfileRequest> findByEmail(String email);

    
}
