package com.group6.moneymanagementbooking.security;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.group6.moneymanagementbooking.model.exception.custom.CustomAuthenticationFailureHandler;
import com.group6.moneymanagementbooking.repository.UsersRepository;
import com.group6.moneymanagementbooking.service.impl.UsersServiceImpl;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired 
    private UsersServiceImpl usersServiceImpl;
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public CustomAuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler(userDetailsService, passwordEncoder(),usersServiceImpl, usersRepository);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
   http.csrf().disable()
                .authorizeHttpRequests((authorize) ->
                        authorize.mvcMatchers("/register").permitAll()
                        .mvcMatchers("/forgot-password").permitAll()
                        .mvcMatchers("/users/**").hasRole("USER")
                        .mvcMatchers("/admins/**").hasRole("ADMIN")
                ).formLogin(
                        form -> form
                                .loginPage("/login")
                                .usernameParameter("email")
                                .passwordParameter("password")
                                .failureUrl("/login?error=true")
                                .failureHandler(authenticationFailureHandler())
                                .defaultSuccessUrl("/", false)
                                .successHandler((request, response, authentication) -> {
                                    Collection<? extends GrantedAuthority> authorities =  authentication.getAuthorities();
                                        for (GrantedAuthority grantedAuthority : authorities) {
                                                if(grantedAuthority.getAuthority().equals("ROLE_ADMIN")){
                                                        response.sendRedirect("/admins/home");
                                                }
                                                if(grantedAuthority.getAuthority().equals("ROLE_USER")){
                                                        response.sendRedirect("/users/dashboard");
                                                }
                                        }
                                }).permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                ).rememberMe().key("Axncmvi2002")
                .tokenValiditySeconds(60*60*24);
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}