package com.group6.moneymanagementbooking.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.group6.moneymanagementbooking.enity.Account;
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
    private final JwtTokenUtil jwtTokenUtil;
    
    @Override
    public AccountDTOResponse loginAccount(AccountDTOLoginRequest accountDTOLoginRequest) {

        Optional<Account> accounOptional = accountRepository.findByEmail(accountDTOLoginRequest.getEmail());
        boolean isAuthen = false;
        if(accounOptional.isPresent()){
            Account account = accounOptional.get();
            if(account.getPassword().equals(accountDTOLoginRequest.getPassword())){
                isAuthen = true;
            }
        }
        if(!isAuthen){
            System.out.println("username and password incorrect.");
        }
      
        return builDtoResponse(accounOptional.get());
    }

    @Override
    public AccountDTOResponse registerAccount(AccountDTORegister accountDTORegister) {
        if(!accountDTORegister.getPassword().equals(accountDTORegister.getRepeatPassword())){
            System.out.println("password invalid");
        }
        Account account = AccountMapper.toAccount(accountDTORegister);

        account = accountRepository.save(account);

       
        return builDtoResponse(account);
    }

    private AccountDTOResponse builDtoResponse(Account account){
        AccountDTOResponse accountDTOResponse = AccountMapper.toAccountDTOResponse(account);
        accountDTOResponse.setToken(jwtTokenUtil.generateToken(account, 24 * 60 * 60));
        return accountDTOResponse;
    }

   
    
}
