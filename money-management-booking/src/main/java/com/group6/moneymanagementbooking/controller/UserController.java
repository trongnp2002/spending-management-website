package com.group6.moneymanagementbooking.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.group6.moneymanagementbooking.enity.Users;
import com.group6.moneymanagementbooking.service.UsersService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/users")

public class UserController {
    @Autowired
    private final UsersService usersService;

    @GetMapping("/home")
    public String goHome(HttpServletRequest request, HttpServletResponse response) {
        return "home";
    }

    @PostMapping("/home")
    public String goHome2(HttpServletRequest request, HttpServletResponse response) {
        return "home";
    }

    @GetMapping("/dashboard")
    public String goDashboard(HttpServletRequest request, HttpServletResponse response) {
        return "dashboard";
    }

    @PostMapping("/dashboard")
    public String goDashboard2(HttpServletRequest request, HttpServletResponse response) {
        return "dashboard";
    }

    @GetMapping("/add-adjust")
    public String setAdjust(Model model) {
        Users user = usersService.getUsers();
        model.addAttribute("user", user);
        return "add-adjust";
    }

    @PostMapping("/add-adjust")
    public String setAdjust(@ModelAttribute Users user){
        usersService.addAdjustForUser(user);
        return "redirect:/list-budget";
    }
}
