package com.group6.moneymanagementbooking.service;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.group6.moneymanagementbooking.enity.OTP;
import com.group6.moneymanagementbooking.repository.OTPRepository;
import com.group6.moneymanagementbooking.util.StringUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OTPService {
    private final OTPRepository otpRepository;

    public void confirm(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        String userOTPInput = request.getParameter("userInput");
        String email = request.getParameter("email");

        Optional<OTP> otpCheckNull = otpRepository.findByEmail(email);
        if (otpCheckNull.isPresent()) {
            OTP otp = otpCheckNull.get();
            if (!StringUtils.isNumberic(userOTPInput)) {
                throw new Exception("OTP is not valid!!!");

            }
            if (LocalDateTime.now().isBefore(otp.getDate_create().plusMinutes(1))) {
                if (!otp.getCode().equals(userOTPInput)) {
                    throw new Exception("Your OTP is not correct");
                }
            } else {
                throw new Exception("Your OTP is expired!!");
            }
            if(otp.isUsed()){
                throw new Exception("This OTP already used");
            }else{
                otp.setUsed(true);
                otpRepository.save(otp);
            }

        }else{
            throw new Exception("Your OTP not exist");
        }
    }

    public void saveOTP(OTP otp) {
        Optional<OTP> checkDuplicate = otpRepository.findByEmail(otp.getEmail());
        if (checkDuplicate.isPresent()) {
            otp.setId(checkDuplicate.get().getId());
        }
        otpRepository.save(otp);
    }
}
