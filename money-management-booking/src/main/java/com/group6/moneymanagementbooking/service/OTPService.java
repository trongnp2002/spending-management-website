package com.group6.moneymanagementbooking.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.group6.moneymanagementbooking.exception.custom.CustomBadRequestException;
import com.group6.moneymanagementbooking.model.CustomError;
import com.group6.moneymanagementbooking.util.StringUtils;

@Service
public class OTPService {

    public void confirm(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        HttpSession session = request.getSession();
        String userOTPInput = request.getParameter("userInput");
        int realOtp = (int) (session.getAttribute("otp1"));
        String email = request.getParameter("email");
        String emailSession = (String) session.getAttribute("account");
        long OTPage = (long)session.getAttribute("OTPage");
        if (!email.equals(emailSession)) {

            throw new Exception("Email not valid");

        } else if (!StringUtils.isNumberic(userOTPInput)) {

            throw new Exception("OTP is not valid!!!");

        } else {
            if(System.currentTimeMillis() - OTPage > 60*1000){
                throw new Exception("Your OTP is expired!!");
            }
            int userOTPNum = Integer.parseInt(userOTPInput);
            if (userOTPNum != realOtp) {

                throw new Exception("Your OTP is not correct");

            }
        }
    }
}
