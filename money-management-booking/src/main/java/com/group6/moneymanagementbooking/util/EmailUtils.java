package com.group6.moneymanagementbooking.util;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class EmailUtils {

    public static void sendVerifyEmail(String to, String subject, String body) throws EmailException {
        HtmlEmail email = new HtmlEmail();
        email.setHostName("imap.gmail.com");
        email.setSmtpPort(587);
        email.setAuthenticator(new DefaultAuthenticator("nguyntrng234@gmail.com", "kubbxerhqhoazngp"));
        email.setStartTLSEnabled(true);
        email.setFrom("nguyntrng234@gmail.com");
        email.addTo(to);
        email.setSubject(subject);
        email.setHtmlMsg(body);
        email.send();

    }

    // Generate a unique identifier for the token
}