package com.group6.moneymanagementbooking;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.group6.moneymanagementbooking.model.Captcha;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(App.class, args);
		Captcha captcha = context.getBean(Captcha.class);
		System.out.println(captcha.getCaptchaCode());
	}


}
