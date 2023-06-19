package com.group6.moneymanagementbooking.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.group6.moneymanagementbooking.dto.mapper.UsersMapper;
import com.group6.moneymanagementbooking.dto.response.UsersForAdminDTOResponse;
import com.group6.moneymanagementbooking.enity.Users;
import com.group6.moneymanagementbooking.repository.UsersRepository;
import com.group6.moneymanagementbooking.service.AdminService;
import com.group6.moneymanagementbooking.util.WebUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final UsersRepository usersRepository;
    private final int PAGE_SIZE = 10;
    private final String HOME = "admin-home";

    @Override
    public List<Users> getPageGroupOfUsers(int page) {

        if (page >= (getMaxPage() - 1)) {
            page = (getMaxPage() - 1);
        }
        return usersRepository.findAll(PageRequest.of(page, PAGE_SIZE)).toList();
    }

    public String changeActiveStatus(int id, String page) {
        updateStatus(id);
        return "redirect:/admins/home/" + page;
    }

    private void updateStatus(int id) {
        Optional<Users> userOptional = usersRepository.findById(id);
        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            user.set_active(!user.is_active());
            usersRepository.save(user);
        }
    }

    @Override
    public String changeActiveStatus(int id, String page, String select, String value) {
        updateStatus(id);
        return "redirect:/admins/search/" + page + "/?select=" + select + "&value=" + value;
    }

    public int getMaxPage() {
        long totalRecords = usersRepository.count();
        int maxPage = (int) Math.ceil((double) totalRecords / PAGE_SIZE);
        return maxPage;
    }

    @Override
    public String searchUsers(Model model, String search, String value, int page) {
        List<Users> groupOfUsers = null;
        page = page - 1;
        switch (search) {

            case "name":
                groupOfUsers = usersRepository
                        .findByFirstNameContainingOrLastNameContaining(value, value, PageRequest.of(page, PAGE_SIZE))
                        .toList();
                break;
            case "email":
                groupOfUsers = usersRepository.findByEmailContaining(value, PageRequest.of(page, PAGE_SIZE)).toList();
                break;
            case "phone":
                groupOfUsers = usersRepository.findByPhoneContaining(value, PageRequest.of(page, PAGE_SIZE)).toList();
                break;
        }

        List<UsersForAdminDTOResponse> list = new ArrayList();
        if (groupOfUsers != null) {
            for (Users user : groupOfUsers) {
                if (user.getRole().equals("ROLE_USER")) {
                    list.add(UsersMapper.toUsersForAdminDTOResponse(user));
                }
            }
        }
        model.addAttribute("txtSearch", value);
        model.addAttribute("select", search);
        page = page + 1;
        return WebUtils.dispartcher(HOME, model, list, page, getSearchMaxPage(search, value));
    }

    private int getSearchMaxPage(String select, String value) {
        List<Users> list = null;
        switch (select) {
            case "name":
                list = usersRepository.findByFirstNameContainingOrLastNameContaining(value, value);
                break;
            case "email":
                list = usersRepository.findByEmailContaining(value);
                break;
            case "phone":
                list = usersRepository.findByPhoneContaining(value);
                break;
        }
        int totalRecords = list.size();
        return (int) Math.ceil((double) totalRecords / 10);
    }

}
