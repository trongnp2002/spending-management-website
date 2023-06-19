package com.group6.moneymanagementbooking.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.group6.moneymanagementbooking.dto.mapper.UsersMapper;
import com.group6.moneymanagementbooking.dto.request.UsersDTORegisterRequest;
import com.group6.moneymanagementbooking.dto.response.UsersForAdminDTOResponse;
import com.group6.moneymanagementbooking.enity.Users;
import com.group6.moneymanagementbooking.model.exception.custom.CustomBadRequestException;
import com.group6.moneymanagementbooking.repository.UsersRepository;
import com.group6.moneymanagementbooking.service.AdminService;
import com.group6.moneymanagementbooking.service.impl.AdminServiceImpl;
import com.group6.moneymanagementbooking.util.WebUtils;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admins")
@CrossOrigin
public class AdminController {
    private final AdminServiceImpl adminServiceImpl;
    private final AdminService usersService;
    private final UsersRepository adminService;
    private final String HOME = "admin-home";

    @GetMapping("/home")
    public String adminHomePage(Model model) throws CustomBadRequestException {
        List<UsersForAdminDTOResponse> groupOfUsersForAdminDTOResponses = new ArrayList();
        for (Users user : adminServiceImpl.getPageGroupOfUsers(0)) {
            if (user.getRole().equals("ROLE_USER")) {
                groupOfUsersForAdminDTOResponses.add(UsersMapper.toUsersForAdminDTOResponse(user));
            }
        }
        return WebUtils.dispartcher(HOME, model, groupOfUsersForAdminDTOResponses, 1, adminServiceImpl.getMaxPage());
    }

    @GetMapping("/home/{page}/{id}")
    public String adminHomePage(@PathVariable("id") int id, @PathVariable("page") String pagging, Model model)
            throws CustomBadRequestException {
        List<UsersForAdminDTOResponse> groupOfUsersForAdminDTOResponses = new ArrayList();
        int page = Integer.parseInt(pagging.substring(4, pagging.length()));

        for (Users user : adminServiceImpl.getPageGroupOfUsers((page - 1))) {
            if (user.getRole().equals("ROLE_USER")) {
                groupOfUsersForAdminDTOResponses.add(UsersMapper.toUsersForAdminDTOResponse(user));
            }
        }
        if (page > adminServiceImpl.getMaxPage()) {
            page = adminServiceImpl.getMaxPage();
        }
        return WebUtils.dispartcher(HOME, model, groupOfUsersForAdminDTOResponses, page, adminServiceImpl.getMaxPage(),
                id);

    }

    @GetMapping("/home/{page}")
    public String adminHomePage(@PathVariable("page") String pagging, Model model)
            throws CustomBadRequestException {
        List<UsersForAdminDTOResponse> groupOfUsersForAdminDTOResponses = new ArrayList();
        int page = Integer.parseInt(pagging.substring(4, pagging.length()));

        for (Users user : adminServiceImpl.getPageGroupOfUsers(page - 1)) {
            if (user.getRole().equals("ROLE_USER")) {
                groupOfUsersForAdminDTOResponses.add(UsersMapper.toUsersForAdminDTOResponse(user));
            }
        }
        if (page > adminServiceImpl.getMaxPage()) {
            page = adminServiceImpl.getMaxPage();
        }
        return WebUtils.dispartcher(HOME, model, groupOfUsersForAdminDTOResponses, page, adminServiceImpl.getMaxPage());

    }

    @GetMapping("/search")
    public String search(Model model, @RequestParam("select") String search, @RequestParam("value") String value) {
        if (search.equals("select")) {
            return "redirect:/admins/home";
        }
        return usersService.searchUsers(model, search, value, 1);
    }

    @GetMapping("/search/{page}")
    public String search(Model model, @PathVariable("page") String pagging,
            @RequestParam("select") String search, @RequestParam("value") String value) {
        int page = Integer.parseInt(pagging.substring(4, pagging.length()));
        return usersService.searchUsers(model, search, value, page);
    }

    @GetMapping("/change-status/{page}/{id}")
    public String changeUserActiveStatus(@PathVariable("id") int id, @PathVariable("page") String page,
            @RequestParam("select") String search, @RequestParam("value") String value) {
        if (search != null && value != null && !search.isEmpty() && !value.isEmpty()) {
            return usersService.changeActiveStatus(id, page, search, value);
        }
        return usersService.changeActiveStatus(id, page);
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

}
