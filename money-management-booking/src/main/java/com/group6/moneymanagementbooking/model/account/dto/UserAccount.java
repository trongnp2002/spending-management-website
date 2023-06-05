package com.group6.moneymanagementbooking.model.account.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserAccount extends User{

    public UserAccount(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        //TODO Auto-generated constructor stub
    }
    
    
}
