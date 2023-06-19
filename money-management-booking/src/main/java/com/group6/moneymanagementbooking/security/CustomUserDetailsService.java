package com.group6.moneymanagementbooking.security;



import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.group6.moneymanagementbooking.enity.Users;
import com.group6.moneymanagementbooking.model.MyUserDetails;
import com.group6.moneymanagementbooking.repository.UsersRepository;


import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UsersRepository accountRepository;

    public CustomUserDetailsService(UsersRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public MyUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Users> user = accountRepository.findByEmail(email);

        if (user != null) {
            Users account = user.get();
            return new MyUserDetails(account);
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
