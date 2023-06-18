package com.group6.moneymanagementbooking.service;

import java.util.List;

import org.springframework.ui.Model;

import com.group6.moneymanagementbooking.enity.Users;

public interface AdminService {
    
      public List<Users> getPageGroupOfUsers(int page);

      public String changeActiveStatus(int id, String page);

            public String changeActiveStatus(int id, String page, String search, String value);

    public String searchUsers(Model model,String search, String value, int page);
}
