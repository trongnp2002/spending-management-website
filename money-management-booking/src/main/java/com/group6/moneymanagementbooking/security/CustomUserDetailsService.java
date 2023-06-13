package com.group6.moneymanagementbooking.security;



import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.group6.moneymanagementbooking.enity.Users;
import com.group6.moneymanagementbooking.repository.UsersRepository;

import java.util.Arrays;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UsersRepository accountRepository;

    public CustomUserDetailsService(UsersRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Users> user = accountRepository.findByEmail(email);

        if (user != null) {
            Users account = user.get();
              SimpleGrantedAuthority authority = new SimpleGrantedAuthority(account.getRole());
            return new org.springframework.security.core.userdetails.User(account.getEmail(),
                    user.get().getPassword(),
                   Arrays.asList(authority));
        }else{
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }

    // private Collection < ? extends GrantedAuthority> mapRolesToAuthorities(Collection <Role> roles) {
    //     Collection < ? extends GrantedAuthority> mapRoles = roles.stream()
    //             .map(role -> new SimpleGrantedAuthority(role.getName()))
    //             .collect(Collectors.toList());
    //     return mapRoles;
    // }
}
