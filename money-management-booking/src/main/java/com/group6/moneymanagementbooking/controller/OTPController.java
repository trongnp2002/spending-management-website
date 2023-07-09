package com.group6.moneymanagementbooking.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.EmailException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.group6.moneymanagementbooking.enity.OTP;
import com.group6.moneymanagementbooking.service.EmailService;
import com.group6.moneymanagementbooking.service.OTPService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/otps")
@RequiredArgsConstructor
public class OTPController {
    private final OTPService otpService;
    private final EmailService emailService;

    @GetMapping(value = "/sendOTP")
    @ResponseStatus(value = HttpStatus.OK)
    public void sendOTPMail(HttpServletRequest request, HttpServletResponse response)
            throws EmailException, IOException {
        String email = (String) request.getParameter("userInput");
        int otpCode = 0;
        Random rand = new Random();
        otpCode = rand.nextInt(1000000);
        String htmlContent = "<h1>Active your account by code here: " + otpCode
                + "</h1> <h2>Note: The email can only exist in 1 minute from the time it started sending!!!!!</h2>";
        PrintWriter out = response.getWriter();

        try {
            emailService.sendVerifyEmail(email, "Dear MyFriend, ", htmlContent);
            System.out.println("Mail sent successfully.");
        } catch (EmailException e) {
            out.println(e.getMessage());
        }
        OTP otp = OTP.builder().date_create(LocalDateTime.now()).email(email).code(String.valueOf(otpCode)).build();
        otpService.saveOTP(otp);
    }

    @GetMapping(value = "confirmOTP")
    @ResponseStatus(value = HttpStatus.OK)
    public void confirmOTP(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        PrintWriter out = response.getWriter();
        try {
            otpService.confirm(request, response, model);
        } catch (Exception e) {
            out.println(e.getMessage());
        }
    }

    @PostMapping("/confirm-otp")
    public String confirmOTPOnPost(HttpServletRequest request, HttpServletResponse response, Model model)
            throws Exception {
        PrintWriter out = response.getWriter();
        try {
            otpService.confirm(request, response, model);
        } catch (Exception e) {
            out.println(e.getMessage());
        }
        return "redirect:/users/new-password";
    }
}
