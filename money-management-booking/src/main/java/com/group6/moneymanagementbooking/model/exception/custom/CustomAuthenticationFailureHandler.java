package com.group6.moneymanagementbooking.model.exception.custom;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.group6.moneymanagementbooking.model.MyUserDetails;
import com.group6.moneymanagementbooking.security.CustomUserDetailsService;

public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private final CustomUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public CustomAuthenticationFailureHandler(CustomUserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {

        String username = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            // Kiểm tra trạng thái isEnabled của người dùng
            MyUserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (userDetails.isEnabled()) {
                // Xử lý các trường hợp khác khi đăng nhập không thành công
                if (passwordEncoder.matches(password, userDetails.getPassword())) {
                    super.onAuthenticationFailure(request, response, exception);
                } else {
                    response.sendRedirect("/login?error=true");
                }
            } else {
                // Người dùng bị vô hiệu hóa
                response.sendRedirect("/login?disabled=true");

            }
        } catch (UsernameNotFoundException e) {
            // Người dùng không tồn tại
            response.sendRedirect("/login?error=true");

        }catch(InternalAuthenticationServiceException iase){
            response.sendRedirect("/login?error=true");
        
        } catch (Exception e) {
            response.sendRedirect("/login?error=true");

        }
    }
}