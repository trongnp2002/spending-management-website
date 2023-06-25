package com.group6.moneymanagementbooking.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.group6.moneymanagementbooking.dto.request.UsersDTOForgotPasswordRequest;
import com.group6.moneymanagementbooking.dto.request.UsersDTORegisterRequest;
import com.group6.moneymanagementbooking.service.UsersService;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class LoginControllerTest {

    @Mock
    private UsersService userService;

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private LoginController loginController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testCreateUsers() throws Exception {
        // Arrange
        Model model = mock(Model.class);
        UsersDTORegisterRequest accountDTORegister = UsersDTORegisterRequest.builder().build();
        when(userService.userRegister(model, accountDTORegister)).thenReturn("success");
        String result = loginController.registerPost(model, accountDTORegister);
        assertEquals("success", result);
        verify(userService).userRegister(model, accountDTORegister);
    }

    @Test
    void testUpdateUserPassword() throws Exception {
        // Arrange
        Model model = mock(Model.class);
        UsersDTOForgotPasswordRequest usersDTOForgotPasswordRequest = UsersDTOForgotPasswordRequest.builder().build();
        when(userService.forgotPassword(model, usersDTOForgotPasswordRequest)).thenReturn("success");

        // Act
        String result = loginController.forgotPasswordPost(model, usersDTOForgotPasswordRequest);

        // Assert
        assertEquals("success", result);
        verify(userService).forgotPassword(model, usersDTOForgotPasswordRequest);
    }
}
