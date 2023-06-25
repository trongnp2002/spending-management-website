package com.group6.moneymanagementbooking.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.group6.moneymanagementbooking.util.SecurityUtils;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/users")

public class UserController {
    @GetMapping("/home")
    public String goHome(HttpServletRequest request, HttpServletResponse response)   {
        return "home";
    }
     @PostMapping("/home")
    public String goHome2(HttpServletRequest request, HttpServletResponse response)   {
        return "home";
    }

        @GetMapping("/dashboard")
    public String goDashboard(HttpServletRequest request, HttpServletResponse response)   {
        System.out.println(SecurityUtils.getCurrentUsername());
        return "dashboard";
    }
     @PostMapping("/dashboard")
    public String goDashboard2(HttpServletRequest request, HttpServletResponse response)  {
        return "dashboard";
    }
    
}
