package com.group6.moneymanagementbooking.security;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;

import com.group6.moneymanagementbooking.enity.Users;
import com.group6.moneymanagementbooking.repository.UsersRepository;
import com.group6.moneymanagementbooking.service.AccountsService;
import com.group6.moneymanagementbooking.util.SecurityUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;
@Order(Ordered.HIGHEST_PRECEDENCE)

public class BeforeAuthenticationFilter extends OncePerRequestFilter {
   private  UsersRepository usersRepository;
   private  AccountsService accountsService;
    public BeforeAuthenticationFilter(UsersRepository usersRepository, AccountsService accountsService){
        this.usersRepository = usersRepository;
        this.accountsService = accountsService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                String email = SecurityUtils.getCurrentUsername();
                Optional<Users> currUser = usersRepository.findByEmail(email); 
                if(currUser.isPresent()){

                    double totalMoney = accountsService.getTotalBalance();
                    String name = currUser.get().getFirstName() +" "+currUser.get().getLastName();
                    request.setAttribute("totalMoney", totalMoney);
                    request.setAttribute("currentUser", name);

                }
        
        // Tiếp tục xử lý các filter và controller tiếp theo
        filterChain.doFilter(request, response);
    }
}