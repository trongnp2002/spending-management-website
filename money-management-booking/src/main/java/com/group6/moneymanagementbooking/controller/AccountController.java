package com.group6.moneymanagementbooking.controller;

import org.springframework.web.bind.annotation.RestController;

import com.group6.moneymanagementbooking.service.AccountService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
}
