package com.group6.moneymanagementbooking.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.group6.moneymanagementbooking.dto.mapper.UsersMapper;
import com.group6.moneymanagementbooking.dto.request.UserDTOEditProfileRequest;
import com.group6.moneymanagementbooking.enity.Users;
import com.group6.moneymanagementbooking.service.UsersService;
import com.group6.moneymanagementbooking.util.SecurityUtils;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/users")

public class UserController {
    @Autowired
    private final UsersService usersService;


    private final PasswordEncoder passwordEncoder;

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
    public String setAdjust(@ModelAttribute Users user) {
        usersService.addAdjustForUser(user);
        return "redirect:/users/list-budget";
    }

    @GetMapping("/profile")
    public String editProfile(Model model, HttpServletRequest request) {
        Users users = usersService.getUserByEmail(SecurityUtils.getCurrentUsername());
        model.addAttribute("userDTOEditProfile", UsersMapper.toUserDTOEditProfileRequest(users));
        return "user-profile";
    }

    @PostMapping("/profile")
    public String registerPost(Model model,
            @ModelAttribute("userDTOEditProfile") UserDTOEditProfileRequest userDTOEditProfile)
            throws Exception {
        String report = "success";
        try {
            usersService.updateInfo(userDTOEditProfile);
        } catch (Exception exception) {
            report = exception.getMessage();
        }
        return "redirect:/users/profile/?report=" + report;
    }

    @PostMapping("/upload-avatar")
    public String uploadAvatar(HttpServletRequest request) {
        String avatar = (String) request.getParameter("avatar");
        if (!avatar.isEmpty()) {
            usersService.uploadAvatar(avatar);
            return "redirect:/users/profile"; // Chuyển hướng về trang profile

        }
        // Xử lý upload không thành công
        return "redirect:/users/profile?mess=image%20cannot%20be%20null!!";
    }

    @GetMapping("/change-password")
    public String changePasswordOnGet(Model model, HttpServletRequest request) {
        return "change-password";
    }

    @PostMapping("/change-password")
    public String changePasswordOnPost(Model model, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String oldPass = request.getParameter("oldPassword");
        String newPass = request.getParameter("newPassword");
        String rePass = request.getParameter("rePassword");
        String report = "?report=success";
        boolean isChangePass = true;

        if (!passwordEncoder.matches(oldPass, usersService.getUsers().getPassword())) {
            report = "?report=wrong-password";
            isChangePass = false;
        }
        if (!newPass.equals(rePass)) {
            report = "?report=not-match";
            isChangePass = false;
        }
        if (newPass.isEmpty() || oldPass.isEmpty() || rePass.isEmpty()) {
            report = "?report=cannot-be-empty";
            isChangePass = false;
        }
        if (isChangePass) {
            usersService.changePassword(passwordEncoder.encode(newPass));
        }
        return "redirect:/users/change-password" + report;
    }

    @GetMapping("/setting")
    public String setting(Model model, HttpServletRequest request) {
        return "setting";
    }

}
