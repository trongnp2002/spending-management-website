package com.group6.moneymanagementbooking.service.impl;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.group6.moneymanagementbooking.enity.Account;
import com.group6.moneymanagementbooking.model.MyUserDetails;
import com.group6.moneymanagementbooking.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService{
    private AccountRepository accountRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Optional<Account> account = accountRepository.findByEmail(username);
        if(account.isPresent()){
            return new MyUserDetails(account.get());
        }
        throw new UsernameNotFoundException("Not found");

    }

    
}
