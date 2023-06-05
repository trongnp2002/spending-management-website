package com.group6.moneymanagementbooking.controller;

import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.EmailException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.group6.moneymanagementbooking.service.OTPService;
import com.group6.moneymanagementbooking.util.EmailUtils;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api/otps")
@RequiredArgsConstructor
public class OTPController {
    private final OTPService otpService;

    @GetMapping(value = "/sendOTP")
    @ResponseStatus(value = HttpStatus.OK)
    public void sendOTPMail(HttpServletRequest request) throws EmailException {
        String email = (String) request.getParameter("userInput");
        int otp = 0;
        HttpSession mySession = request.getSession();
        Random rand = new Random();
        otp = rand.nextInt(1000000);
        String htmlContent = "<h1>Active your account by code here: " + otp
                + "</h1> <h2>Note: The email can only exist in 1 minute from the time it started sending!!!!!</h2>";

        EmailUtils.sendVerifyEmail(email, "Dear MyFriend, ", htmlContent);

        System.out.println("Mail sent successfully.");

        mySession.setAttribute("otp1", otp);
        mySession.setAttribute("account", email);
        mySession.setAttribute("OTPage", System.currentTimeMillis());
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

}
