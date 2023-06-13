package com.group6.moneymanagementbooking.service.impl;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.group6.moneymanagementbooking.dto.mapper.UsersMapper;
import com.group6.moneymanagementbooking.dto.request.UsersDTORegisterRequest;
import com.group6.moneymanagementbooking.enity.Users;
import com.group6.moneymanagementbooking.model.exception.custom.CustomBadRequestException;
import com.group6.moneymanagementbooking.repository.UsersRepository;
import com.group6.moneymanagementbooking.service.UsersService;
import com.group6.moneymanagementbooking.util.StringUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {
   private final UsersRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

 

    @Override
    public String registerAccount(Model model, UsersDTORegisterRequest accountDTORegister) throws Exception {

        String report = "<p style='padding-left:20px; height: 100%; line-height:100%;' > Warning: ";
        int report_length = report.length();
        report += registerCheckCondition(accountDTORegister);
        if (report.length() > report_length) {
            report += "</p>";
            model.addAttribute("report", report);
            model.addAttribute("accountDTORegister", accountDTORegister);
            return "register";
        }
        Users account = UsersMapper.toUsers(accountDTORegister);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account = accountRepository.save(account);
        return "redirect:/login";
    }

    @Override
    public void checkEmailCondition(HttpServletRequest request, HttpServletResponse response)
            throws IOException, CustomBadRequestException {
        String userEmail = request.getParameter("userEmail");

        try (PrintWriter out = response.getWriter()) {
            if (StringUtils.patternMatchesEmail(userEmail,
                    "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}(.[a-z]{2,3})+$|^$")) {
                if (checkEmailDuplicate(userEmail)) {
                    out.println("<h5 style='color:red;'>Your email already exist!!!</h5>");
                } else {
                    out.println("<h5 style='color:green;'>Your email address is accepted!!!</h5>");
                }
            } else {
                out.println("<h5 style='color:red;'>Your email is invalid!!!</h5>");
            }

        }

    }

    @Override
    public void checkPhoneCondition(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userPhone = request.getParameter("userPhone");
        try (PrintWriter out = response.getWriter()) {
            if (!StringUtils.isDigit(userPhone)) {
                out.println("<h5 style='color:red;'>Your phone number must be digit!!!</h5>");
            } else {
                if (userPhone.length() > 10 || userPhone.length() < 10) {
                    out.println("<h5 style='color:red;'>Your phone number must have 10 digit!!!</h5>");
                } else {
                    if (checkPhoneDuplicate(userPhone)) {
                        out.println("<h5 style='color:red;'>Your phone number already exist!!!</h5>");
                    } else {
                        out.println("<h5 style='color:green;'>Your phone number is accepted!!!</h5>");
                    }
                }
            }

        }
    }

    private boolean checkPhoneDuplicate(String phone) {
        Optional<Users> accouOptional = accountRepository.findByPhone(phone);
        if (accouOptional.isPresent()) {
            return true;
        }
        return false;
    }

    private boolean checkEmailDuplicate(String email) {
        Optional<Users> accouOptional = accountRepository.findByEmail(email);
        if (!accouOptional.isPresent()) {

            return false;
        }
        return true;
    }

    private String registerCheckCondition(UsersDTORegisterRequest accountDTORegister) {
        String report = "";
        if (checkEmailDuplicate(accountDTORegister.getEmail())) {
            report += "This email already exits!! </br>";
        }
        if (checkPhoneDuplicate(accountDTORegister.getPhone())) {
            report += "This phone already in use!! </br>";
        }
        if (!accountDTORegister.getPassword().equals(accountDTORegister.getRepeatPassword())) {
            report += "Password and password is different!!! </br>";
        }
        if (accountDTORegister.getPassword().isEmpty() || accountDTORegister.getRepeatPassword().isEmpty()) {
            report += "Password/Re-password cannot be empty!!! </br>";
        }
        return report;
    }


}
