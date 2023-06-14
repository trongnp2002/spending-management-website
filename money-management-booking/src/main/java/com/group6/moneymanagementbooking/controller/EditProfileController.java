package com.group6.moneymanagementbooking.controller;

import java.io.IOException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import com.group6.moneymanagementbooking.model.exception.custom.CustomBadRequestException;
// import com.group6.moneymanagementbooking.service.EditProfileService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/editprofile")
@CrossOrigin
public class EditProfileController {
    // private final EditProfileService editProfileService;

    @GetMapping("")
    public String editProfile(Model model) throws CustomBadRequestException {
      //  AccountDTOLoginRequest accountDTOLoginRequest =  AccountDTOLoginRequest.builder().build();
     //   model.addAttribute("accountDTOLoginRequest", accountDTOLoginRequest);
        return "editprofile";
    }


    @GetMapping("/displayImage/{photo}")
    public ResponseEntity<Resource> displayImage(@PathVariable("photo") String photo) throws IOException {
        Resource resource = new ClassPathResource("static/image/" + photo);
        
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }
}
