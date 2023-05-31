package com.group6.moneymanagementbooking.model;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class Captcha {
    private static final int CAPTCHA_LENGTH = 6;
    private String captchaCode;


    public Captcha() {
       setCaptchaCode();
    }
    
    public String getCaptchaCode() {
        return this.captchaCode;
    }
    public void setCaptchaCode(){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder captcha = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < CAPTCHA_LENGTH; i++) {
            int index = random.nextInt(characters.length());
            captcha.append(characters.charAt(index));
        }

        captchaCode = captcha.toString();
    }

    
}

   
