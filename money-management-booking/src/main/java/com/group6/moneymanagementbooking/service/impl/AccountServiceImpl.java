package com.group6.moneymanagementbooking.service.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.group6.moneymanagementbooking.enity.Account;
import com.group6.moneymanagementbooking.exception.custom.CustomBadRequestException;
import com.group6.moneymanagementbooking.model.CustomError;
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
    private String token;

    @Override
    public String loginAccount(HttpServletRequest request, HttpServletResponse response,
            AccountDTOLoginRequest accountDTOLoginRequest) throws CustomBadRequestException {

        Optional<Account> accounOptional = accountRepository.findByEmail(accountDTOLoginRequest.getEmail());

        if (accounOptional.isPresent()) {
            Account account = accounOptional.get();
            if (!passwordEncoder.matches(accountDTOLoginRequest.getPassword(), account.getPassword())) {
                throw new CustomBadRequestException(
                        CustomError.builder().code("404").message("Username or Passwrod is incorrect!!!").build());
            }
        } else {
            throw new CustomBadRequestException(
                    CustomError.builder().code("404").message("This account does not exits").build());

        }
        AccountDTOResponse accountDTOResponse = builDtoResponse(accounOptional.get());
        token = accountDTOResponse.getToken();

        Cookie newCookie = new Cookie("Authorization", token);
        newCookie.setMaxAge(24 * 60 * 60);
        response.addCookie(newCookie);

        return "redirect:/api/accounts/home";
    }

    @Override
    public String registerAccount(AccountDTORegister accountDTORegister) throws Exception {
        if (!accountDTORegister.getPassword().equals(accountDTORegister.getRepeatPassword())) {
            throw new CustomBadRequestException(
                    CustomError.builder().code("404").message("Re-password and password is different!!!").build());
        }
        Account account = AccountMapper.toAccount(accountDTORegister);
        if (!checkEmail(account.getEmail())) {
            throw new Exception("This email already exits!!");
        }
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account = accountRepository.save(account);
        return "redirect:/api/accounts/home";
    }

    private AccountDTOResponse builDtoResponse(Account account) {
        AccountDTOResponse accountDTOResponse = AccountMapper.toAccountDTOResponse(account);
        accountDTOResponse.setToken(JwtTokenUtil.generateToken(account, 24 * 60 * 60));
        return accountDTOResponse;

    }

    public boolean checkEmail(String email) {
        Optional<Account> accouOptional = accountRepository.findByEmail(email);
        if (accouOptional.get() == null) {
          
            return false;
        }
        return true;
    }

    @Override
    public void checkEmail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userEmail = request.getParameter("userEmail");
        Optional<Account> accouOptional = accountRepository.findByEmail(userEmail);

        try (PrintWriter out = response.getWriter()) {
            if (checkEmail(userEmail)) {
                out.println("This email already exits!!");
            }else{
                out.println("Email valid");
            }
        }
      
    }

}
