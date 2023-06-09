package com.group6.moneymanagementbooking.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.group6.moneymanagementbooking.enity.Account;
import com.group6.moneymanagementbooking.model.TokenPayload;
import com.group6.moneymanagementbooking.repository.AccountRepository;
import com.group6.moneymanagementbooking.util.JwtTokenUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {
    // protected void doFilterInternal(HttpServletRequest request,
    // HttpServletResponse response, FilterChain filterChain)
    // throws ServletException, IOException {
    // filterChain.doFilter(request, response);
    // }

    private final AccountRepository accountRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        Cookie cookies[] = request.getCookies();
        String requestTokenHeader = "";

        String token = "";
        TokenPayload tokenPayload = null;

        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("Authorization")) {
                    requestTokenHeader = cookie.getValue();
                }
            }
            if (requestTokenHeader.length() >0) {
                token = requestTokenHeader;

                try {
                    tokenPayload = JwtTokenUtil.getTokenPayLoad(token);
                    HttpSession session = request.getSession();
                    session.setAttribute("tokenPayLoad", tokenPayload);

                } catch (SignatureException se) {
                    System.out.println("Invalid JWT signature");
                } catch (IllegalArgumentException illae) {
                    System.out.println("Unable to get jwt");
                } catch (ExpiredJwtException exe) {
                    System.out.println("Token has expired");
                }
            }
        } else {
            System.out.println("Cookie not exits");
        }
        if (tokenPayload != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Optional<Account> accountOptional = accountRepository.findById(tokenPayload.getAccountId());

            if (accountOptional.isPresent()) {
                Account account = accountOptional.get();
                if (JwtTokenUtil.validate(token, account)) {
                    List<SimpleGrantedAuthority> authority = new ArrayList<>();
                 
                    authority.add(new SimpleGrantedAuthority(account.getRoll()));
                    UserDetails userDetails = new org.springframework.security.core.userdetails.User(account.getEmail(),
                            account.getPassword(), authority);
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, authority);
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        }
        filterChain.doFilter(request, response);

    }

}