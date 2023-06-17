package com.group6.moneymanagementbooking.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.group6.moneymanagementbooking.dto.mapper.UsersMapper;
import com.group6.moneymanagementbooking.dto.request.UserDTOEditProfileRequest;
import com.group6.moneymanagementbooking.enity.Users;
import com.group6.moneymanagementbooking.repository.UsersRepository;
import com.group6.moneymanagementbooking.service.EditProfileService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EditProfileServiceImpl implements EditProfileService {

     private final UsersRepository usersRepository;

     @Override
     public UserDTOEditProfileRequest getUserByEmail(String email) {
          Optional<Users> usersOptional = usersRepository.findByEmail(email);
          Users users = usersOptional.get();
          UserDTOEditProfileRequest userDTOEditProfile = UsersMapper.toUserDTOEditProfileRequest(users);
          return userDTOEditProfile;
     }

     @Override
     public void updateInfo(UserDTOEditProfileRequest userDTOEditProfile) {
         usersRepository.save(UsersMapper.toUsers(userDTOEditProfile));
     } 

}
