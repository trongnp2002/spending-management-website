package com.group6.moneymanagementbooking.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.group6.moneymanagementbooking.dto.request.UserDTOEditProfileRequest;
// import com.group6.moneymanagementbooking.service.EditProfileService;
import com.group6.moneymanagementbooking.service.EditProfileService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor

@CrossOrigin
public class EditProfileController {
    private final EditProfileService editProfileService;

    @GetMapping("/editprofile")
    public String editProfile(Model model, HttpServletRequest request) {
        String email = "hung@gmail.com";
        UserDTOEditProfileRequest userDTOEditProfile = editProfileService.getUserByEmail(email);
        model.addAttribute("userDTOEditProfile", userDTOEditProfile);
        return "editprofile";
    }

    @PostMapping("/editprofile")
    public void registerPost(@ModelAttribute("userDTOEditProfile") UserDTOEditProfileRequest userDTOEditProfile)
            throws Exception {
        editProfileService.updateInfo(userDTOEditProfile);
    }

    @GetMapping("/displayImage/{photo}")
    public ResponseEntity<Resource> displayImage(@PathVariable("photo") String photo) throws IOException {
        Resource resource = new ClassPathResource("static/image/" + photo);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }
}
