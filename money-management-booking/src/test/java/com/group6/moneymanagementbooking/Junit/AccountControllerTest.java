package com.group6.moneymanagementbooking.Junit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.group6.moneymanagementbooking.controller.AccountController;
import com.group6.moneymanagementbooking.enity.Accounts;
import com.group6.moneymanagementbooking.service.AccountsService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Optional;

class AccountControllerTest {
    @Mock
    private AccountsService accountsService;

    @Mock
    private Model model;

    @InjectMocks
    private AccountController accountController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

@Test
void testAddAccount() {
    Accounts accounts = new Accounts();
    when(accountsService.addAccounts(accounts)).thenReturn(accounts);

    String result = accountController.addAccount(accounts);

    assertEquals("success", result);
    verify(accountsService, times(1)).addAccounts(accounts);
}

    @Test
    void testUpdateActive() {
        int id = 1;
        boolean action = true;

        String result = accountController.updateActive(id, action, model);

        assertEquals("redirect:/list-account", result);
        verify(accountsService, times(1)).updateActiveById(action, id);
    }

    @Test
    void testReadAccount() {
        int id = 1;
        Accounts account = new Accounts();
        when(accountsService.findOptinalById(id)).thenReturn(Optional.of(account));
        String result = accountController.index(model);
        assertEquals("listaccount", result);
    }

    @Test
void testDeleteAccount() {
    int id = 1;

    String result = accountController.delete(id);

    assertEquals("redirect:/list-account", result);
    verify(accountsService, times(1)).deleteById(id);
}
}
