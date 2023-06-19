package com.group6.moneymanagementbooking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group6.moneymanagementbooking.dto.request.UserDTOEditProfileRequest;
import com.group6.moneymanagementbooking.enity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    public Optional<Users> findByEmail(String email);

    public Page<Users> findByEmailContaining(String email, Pageable pageable);

    public List<Users> findByEmailContaining(String email);

    public Page<Users> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName,
            Pageable pageable);

    public List<Users> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName);

    public Page<Users> findByPhoneContaining(String phone, Pageable pageable);

    public List<Users> findByPhoneContaining(String phone);

    public Optional<Users> findByPhone(String phone);

}
