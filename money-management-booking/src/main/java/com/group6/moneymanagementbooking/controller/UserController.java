package com.group6.moneymanagementbooking.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.group6.moneymanagementbooking.model.exception.custom.CustomBadRequestException;
import com.group6.moneymanagementbooking.repository.UsersRepository;
import com.group6.moneymanagementbooking.util.SecurityUtils;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/users")

public class UserController {
    private final UsersRepository usersRepository;
    @GetMapping("/home")
    public String goHome(HttpServletRequest request, HttpServletResponse response) throws CustomBadRequestException {
        return "home";
    }
     @PostMapping("/home")
    public String goHome2(HttpServletRequest request, HttpServletResponse response) throws CustomBadRequestException {
        return "home";
    }

        @GetMapping("/dashboard")
    public String goDashboard(HttpServletRequest request, HttpServletResponse response) throws CustomBadRequestException {
        System.out.println(SecurityUtils.getCurrentUsername());
        return "dashboard";
    }
     @PostMapping("/dashboard")
    public String goDashboard2(HttpServletRequest request, HttpServletResponse response) throws CustomBadRequestException {
        return "dashboard";
    }
    
}
