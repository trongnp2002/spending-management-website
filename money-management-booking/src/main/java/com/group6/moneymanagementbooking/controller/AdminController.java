package com.group6.moneymanagementbooking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.group6.moneymanagementbooking.dto.mapper.UsersMapper;
import com.group6.moneymanagementbooking.dto.request.UsersDTORegisterRequest;
import com.group6.moneymanagementbooking.enity.Users;
import com.group6.moneymanagementbooking.repository.UsersRepository;
import com.group6.moneymanagementbooking.service.AdminService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admins")
@CrossOrigin
public class AdminController {
    private final AdminService usersService;
    private final UsersRepository adminService;

    @GetMapping("/home")
    public String adminHomePage(Model model) {
        return usersService.getPageGroupOfUsers(model, 1);
    }

    @GetMapping("/home/{page}")
    public String adminHomePage(@PathVariable("page") String pagging, Model model) {
        int page = Integer.parseInt(pagging.substring(4, pagging.length()));
        return usersService.getPageGroupOfUsers(model, page);
    }

    @GetMapping("/search")
    public String search(Model model, @RequestParam("select") String searchBy, @RequestParam("value") String value,
            @RequestParam(value = "nonlocked", required = false) Boolean nonlocked,
            @RequestParam(value = "isactive", required = false) Boolean isActive) {
        if (searchBy.equals("select")) {
            return "redirect:/admins/home";
        }
        if (nonlocked != null) {
            return usersService.searchListUsersNonLock(model, searchBy, value, 1, nonlocked);
        }
        if (isActive != null) {
            return usersService.searchListUsersActive(model, searchBy, value, 1, isActive);
        }

        return usersService.searchUsers(model, searchBy, value, 1);
    }

    @GetMapping("/search/{page}")
    public String search(Model model, @PathVariable("page") String pagging,
            @RequestParam("select") String searchBy, @RequestParam("value") String value,
            @RequestParam(value = "nonlocked", required = false) Boolean nonlocked,
            @RequestParam(value = "isactive", required = false) Boolean isActive) {

        int page = Integer.parseInt(pagging.substring(4, pagging.length()));

        if (nonlocked != null) {
            return usersService.searchListUsersNonLock(model, searchBy, value, page, nonlocked);
        }
        if (isActive != null) {
            return usersService.searchListUsersActive(model, searchBy, value, page, isActive);
        }
        return usersService.searchUsers(model, searchBy, value, page);
    }

    @GetMapping("/change-status/{page}/{id}")
    public String changeUserActiveStatus(Model model,@PathVariable("id") int id, @PathVariable("page") String page,
            @RequestParam(value = "select", required = false) String searchBy,
            @RequestParam(value = "value", required = false) String value,
            @RequestParam(value = "nonlocked", required = false) Boolean nonlocked,
            @RequestParam(value = "isactive", required = false) Boolean isActive) {
        int pagging = Integer.parseInt(page.substring(4, page.length()));

        if (nonlocked != null) {
            if (searchBy != null && value != null) {
                return usersService.changeActiveStatusNonLocked(model, id, pagging, searchBy, value, nonlocked);
            } else {
                return usersService.changeActiveStatusNonLocked(model, id, pagging, nonlocked);
            }
        }
        if (isActive != null) {
            if (searchBy != null && value != null) {
                return usersService.changeActiveStatusActivePage(model, id, pagging, searchBy, value, isActive);
            } else {
                return usersService.changeActiveStatusActivePage(model, id, pagging, isActive);
            }
        }
        if (searchBy != null && value != null) {
            return usersService.changeActiveStatus(id, page, searchBy, value);
        }
        return usersService.changeActiveStatus(id, page);
    }

    @GetMapping("/list-locked")
    public String getUsersLock(@RequestParam("nonlocked") Boolean isLock, Model model) {

        return usersService.getListOfLockedUser(model, isLock, 1);
    }

    @GetMapping("/list-status")
    public String getListStatus(@RequestParam("isactive") Boolean isActive, Model model) {
        return usersService.getListOfUnActiveUser(model, isActive, 1);
    }

    @GetMapping("/list-locked/{page}")
    public String getUsersLockByPage(@RequestParam("nonlocked") Boolean islocked, Model model,
            @PathVariable("page") String pagging) {
        int page = Integer.parseInt(pagging.substring(4, pagging.length()));
        return usersService.getListOfLockedUser(model, islocked, page);
    }

    @GetMapping("/list-status/{page}")
    public String getUsersActiveByPage(@RequestParam("isactive") Boolean isactive, Model model,
            @PathVariable("page") String pagging) {
        int page = Integer.parseInt(pagging.substring(4, pagging.length()));
        return usersService.getListOfUnActiveUser(model, isactive, page);
    }

    @GetMapping("/addfast")
    public String addFast() {
        for (int i = 1; i <= 100; i++) {
            String fist_name = "trong" + i;
            String last_name = "nguyen" + i;
            String email = "trongnguyen" + i + "@gmail.com";
            String password = "$2a$10$tCfT.g3xn99ISNlniDJy/ehNibU5vCqKS.5nDWtmpfozWHpOHo/fu";
            String address = "Ninh Binh";
            String phone = String.valueOf(1000000000 + i);
            UsersDTORegisterRequest usersDTORegisterRequest = new UsersDTORegisterRequest(fist_name, last_name, email,
                    password, password, phone, address);
            Users users = UsersMapper.toUsers(usersDTORegisterRequest);
            adminService.save(users);
        }
        String statusId = "#status" + 100;
        return "redirect:/admins/home/" + statusId;
    }

    // private
}
