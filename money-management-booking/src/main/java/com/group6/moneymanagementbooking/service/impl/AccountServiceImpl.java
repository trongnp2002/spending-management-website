package com.group6.moneymanagementbooking.service.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.group6.moneymanagementbooking.enity.Account;
import com.group6.moneymanagementbooking.exception.custom.CustomBadRequestException;
import com.group6.moneymanagementbooking.model.account.dto.AccountDTOLoginRequest;
import com.group6.moneymanagementbooking.model.account.dto.AccountDTORegister;
import com.group6.moneymanagementbooking.model.account.dto.AccountDTOResponse;
import com.group6.moneymanagementbooking.model.account.mapper.AccountMapper;
import com.group6.moneymanagementbooking.repository.AccountRepository;
import com.group6.moneymanagementbooking.service.AccountService;
import com.group6.moneymanagementbooking.util.JwtTokenUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String loginAccount(Model model, HttpServletRequest request, HttpServletResponse response,
            AccountDTOLoginRequest accountDTOLoginRequest) throws CustomBadRequestException {
        String token = "";
        Optional<Account> accounOptional = accountRepository.findByEmail(accountDTOLoginRequest.getEmail());
        if (accounOptional.isPresent()
                && passwordEncoder.matches(accountDTOLoginRequest.getPassword(), accounOptional.get().getPassword())) {
            AccountDTOResponse accountDTOResponse = builDtoResponse(accounOptional.get());
            token = accountDTOResponse.getToken();
            Cookie newCookie = new Cookie("Authorization", token);
            newCookie.setMaxAge(24 * 60 * 60);
            response.addCookie(newCookie);
            return "redirect:/api/user/home";
        }

        String report = "<p style='padding-left:20px; height: 100%; line-height:100%;' > Warning: Email or password not correct!!!</p>";
        model.addAttribute("report", report);
        return "login";

    }

    @Override
    public String registerAccount(Model model, AccountDTORegister accountDTORegister) throws Exception {

        String report = "<p style='padding-left:20px; height: 100%; line-height:100%;' > Warning: ";
        int report_length = report.length();
        report += registerCheckCondition(accountDTORegister);
        if (report.length() > report_length) {
            report += "</p>";
            model.addAttribute("report", report);
            model.addAttribute("accountDTORegister", accountDTORegister);
            return "register";
        }
        Account account = AccountMapper.toAccount(accountDTORegister);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account = accountRepository.save(account);
        return "redirect:/api/accounts/login";
    }

    private AccountDTOResponse builDtoResponse(Account account) {
        AccountDTOResponse accountDTOResponse = AccountMapper.toAccountDTOResponse(account);
        accountDTOResponse.setToken(JwtTokenUtil.generateToken(account, 24 * 60 * 60));
        return accountDTOResponse;

    }

    @Override
    public void checkEmail(HttpServletRequest request, HttpServletResponse response)
            throws IOException, CustomBadRequestException {
        String userEmail = request.getParameter("userEmail");

        try (PrintWriter out = response.getWriter()) {
            if (checkEmail(userEmail)) {
                out.println("<h5 style='color:red;'>Your email already exist!!!</h5>");
            } else {
                out.println("<h5 style='color:green;'>Your email address is accepted!!!</h5>");
            }
        }

    }

    @Override
    public void checkPhone(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userPhone = request.getParameter("userPhone");
        try (PrintWriter out = response.getWriter()) {
            if (checkPhone(userPhone)) {
                out.println("<h5 style='color:red;'>Your phone number already exist!!!</h5>");
            } else {
                out.println("<h5 style='color:green;'>Your phone number is accepted!!!</h5>");

            }
        }
    }

    private boolean checkPhone(String phone) {
        Optional<Account> accouOptional = accountRepository.findByPhone(phone);
        if (accouOptional.isPresent()) {
            return true;
        }
        return false;
    }

    private boolean checkEmail(String email) {
        Optional<Account> accouOptional = accountRepository.findByEmail(email);
        if (!accouOptional.isPresent()) {

            return false;
        }
        return true;
    }

    private String registerCheckCondition(AccountDTORegister accountDTORegister) {
        String report = "";
        if (checkEmail(accountDTORegister.getEmail())) {
            report += "This email already exits!! </br>";
        }
        if (checkPhone(accountDTORegister.getPhone())) {
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
