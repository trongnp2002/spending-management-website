package com.group6.moneymanagementbooking.service.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.group6.moneymanagementbooking.dto.mapper.UsersMapper;
import com.group6.moneymanagementbooking.dto.response.UsersForAdminDTOResponse;
import com.group6.moneymanagementbooking.enity.Users;
import com.group6.moneymanagementbooking.repository.UsersRepository;
import com.group6.moneymanagementbooking.service.AdminService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final UsersRepository usersRepository;
    private final int PAGE_SIZE = 10;
// getting
    //1. get group of users original
    @Override
    public List<UsersForAdminDTOResponse> getPageGroupOfUsers(Model model, int page) {
        int totalPage = getMaxPage();
        if (page > totalPage)
            page = totalPage;
        if (totalPage == 0)
            page = 1;
        model.addAttribute("data2", totalPage);
        List<Users> list = usersRepository.findAll(PageRequest.of(page - 1, PAGE_SIZE)).toList();
        return UsersMapper.toUsersForAdminDTOResponses(list);
    }

    //2. get group of locked/nonlock users
    @Override
    public List<UsersForAdminDTOResponse>  getGroupOfLockedUser(Model model, Boolean islock, int page) {
        int totalPage = getSearchMaxPage("locked", islock.toString());
        if (page >= totalPage)
            page = totalPage;
        if (totalPage == 0)
            page = 1;
        List<Users> listOfLockedUser = usersRepository.findByNonLocked(islock, PageRequest.of((page - 1), PAGE_SIZE))
                .toList();
        model.addAttribute("data2", totalPage);
        return UsersMapper.toUsersForAdminDTOResponses(listOfLockedUser);
    }

    //3. get group of active/inactive users
    @Override
    public List<UsersForAdminDTOResponse> getGroupOfActiveUsers(Model model, Boolean isActive, int page) {
        int totalPage = getSearchMaxPage("active", isActive.toString());
        if (page >= totalPage) {
            page = totalPage;
        }
        if (totalPage == 0)
            page = 1;
        List<Users> listOfActiveUser = usersRepository.findByActive(isActive, PageRequest.of((page - 1), PAGE_SIZE))
                .toList();
        model.addAttribute("data2", totalPage);
        return UsersMapper.toUsersForAdminDTOResponses(listOfActiveUser);

    }
// updating
    @Override
    public void changeActiveStatus(HttpServletResponse response, int id) throws IOException {
        String data = "";
        updateStatus(id);
        Optional<Users> usersOptional = usersRepository.findById(id);
        if (usersOptional.isPresent()) {
            Users users = usersOptional.get();
            data = tableCellHtmlLayout(users);
        }
        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()) {
            out.println(data);
        }
    }

// searching
    //1. search origin
    @Override
    public List<UsersForAdminDTOResponse> searchUsers(Model model, String searchBy, String value, int page) {
        List<Users> groupOfUsers = null;
        int totalPage = getSearchMaxPage(searchBy, value);
        if (page > totalPage)
            page = totalPage;
        if (totalPage == 0)
            page = 1;
        page = page - 1;
        switch (searchBy) {
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
        model.addAttribute("txtSearch", value);
        model.addAttribute("select", searchBy);
        model.addAttribute("data2", totalPage);
        return UsersMapper.toUsersForAdminDTOResponses(groupOfUsers);
    }
    //2. search user lock/nonlock
    @Override
    public List<UsersForAdminDTOResponse> searchInGroupOfLockedUsers(Model model, String searchBy, String value, int page,
            Boolean nonLock) {
        List<Users> groupOfUsers = null;
        boolean isLock = !nonLock;
        int totalPage = getSearchMaxPageLocked(searchBy, value, isLock);
        if (page > totalPage)
            page = totalPage;
        if (totalPage == 0)
            page = 1;
        page = page - 1;
        switch (searchBy) {
            case "name":
                groupOfUsers = usersRepository
                        .findByFirstNameContainingOrLastNameContainingAndNonLocked(value, value, isLock,
                                PageRequest.of(page, PAGE_SIZE))
                        .toList();
                break;
            case "email":
                groupOfUsers = usersRepository
                        .findByEmailContainingAndNonLocked(value, isLock, PageRequest.of(page, PAGE_SIZE)).toList();
                break;
            case "phone":
                groupOfUsers = usersRepository
                        .findByPhoneContainingAndNonLocked(value, isLock, PageRequest.of(page, PAGE_SIZE)).toList();
                break;
        }
        model.addAttribute("txtSearch", value);
        model.addAttribute("select", searchBy);
        model.addAttribute("data2", totalPage);
        return UsersMapper.toUsersForAdminDTOResponses(groupOfUsers);
    }

    //3. search user active/inactive
    @Override
    public List<UsersForAdminDTOResponse> searchInGroupOfActiveUsers(Model model, String searchBy, String value, int page,
            Boolean isActive) {
        List<Users> groupOfUsers = null;
        isActive = !isActive;
        int totalPage = getSearchMaxPageActive(searchBy, value, isActive);
        if (page > totalPage)
            page = totalPage;
        if (totalPage == 0)
            page = 1;
        page = page - 1;
        switch (searchBy) {
            case "name":
                groupOfUsers = usersRepository.findByFirstNameContainingOrLastNameContainingAndActive(
                        value, value, isActive, PageRequest.of(page, PAGE_SIZE)).toList();
                break;
            case "email":
                groupOfUsers = usersRepository.findByEmailContainingAndActive(
                        value, isActive, PageRequest.of(page, PAGE_SIZE)).toList();
                break;
            case "phone":
                groupOfUsers = usersRepository.findByPhoneContainingAndActive(
                        value, isActive, PageRequest.of(page, PAGE_SIZE)).toList();
                break;
        }
        model.addAttribute("txtSearch", value);
        model.addAttribute("select", searchBy);
        model.addAttribute("data2", totalPage);
        return UsersMapper.toUsersForAdminDTOResponses(groupOfUsers);

    }

// for update status
    // update
    private void updateStatus(int id) {
        Optional<Users> userOptional = usersRepository.findById(id);
        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            user.setActive(!user.isActive());
            usersRepository.save(user);
        }
    }

    // html layout
    private String tableCellHtmlLayout(Users users) {
        String data = "<tr id = 'user" + users.getId() + "'";
        if (users.isActive()) {
            data += "class='red-row'";
        }
        data += "> \n";
        data = data + "<td>" + users.getId() + "</td> \n"
                + "<td>" + users.getFirstName() + " " + users.getLastName() + "</td> \n"
                + "<td>" + users.getEmail() + "</td>"
                + "<td class='admin-center-text'>" + users.getPhone() + "</td> \n"
                + "<td class='admin-center-text'>" + users.getFailed_attempt() + "</td> \n"
                + "<td class='status'> \n";
        if (users.isNonLocked()) {
            data = data + "<p class='positive-status'>Non-lock</p> \n";
        } else {
            data = data + "  <p class='negative-status'>Lock</p> \n";
        }
        data = data + "  </td> \n";
        if (users.getLockTime() != null) {
            data = data + "<td>" + users.getLockTime() + "</td> \n";
        } else {
            data = data + "<td></td> \n";
        }
        data = data + "<td class='status'> \n";
        if (users.isActive()) {
            data = data + " <p class='positive-status'>Active</p> \n";
        } else {
            data = data + "<p class='negative-status'>De-active</p>";
        }
        data = data + "</td>\n" + "<td style='justify-content: center; text-align: center;' "
                + "class='status' onclick='changeStatus(" + users.getId() + "," + users.isActive() + ")'> \n";
        if (users.isActive()) {
            data = data + "<button class='disabled-status'> Disable </button> \n";
        } else {
            data = data + "<button class='enabled-status'> Enable </button> \n";
        }
        data = data + "</td></tr>";
        return data;
    }

// get total page
    // 1. Origin total page
    private int getMaxPage() {
        long totalRecords = usersRepository.count();
        int maxPage = (int) Math.ceil((double) totalRecords / PAGE_SIZE);
        return maxPage;
    }

    // 2. Total Page when searching/or all of group active/inactive or
    // locked/nonlock user
    private int getSearchMaxPage(String searchBy, String value) {
        List<Users> list = null;
        switch (searchBy) {
            case "name":
                list = usersRepository.findByFirstNameContainingOrLastNameContaining(value, value);
                break;
            case "email":
                list = usersRepository.findByEmailContaining(value);
                break;
            case "phone":
                list = usersRepository.findByPhoneContaining(value);
                break;
            case "active":
                boolean isActive = value.contains("true");
                list = usersRepository.findByActive(isActive);
                break;
            case "locked":
                boolean isLock = value.contains("true");
                list = usersRepository.findByNonLocked(isLock);
                break;
        }
        int totalRecords = 0;
        if (list != null) {
            totalRecords = list.size();
        }
        return (int) Math.ceil((double) totalRecords / 10);
    }

    // 3. Total page when searching in group of active/inactive users
    private int getSearchMaxPageActive(String searchBy, String value, boolean isActive) {
        List<Users> list = null;
        switch (searchBy) {
            case "name":
                list = usersRepository
                        .findByFirstNameContainingOrLastNameContainingAndActive(value, value, isActive);
                break;
            case "email":
                list = usersRepository.findByEmailContainingAndActive(value, isActive);
                break;
            case "phone":
                list = usersRepository.findByPhoneContainingAndActive(value, isActive);
                break;
        }
        int totalRecords = 0;
        if (list != null) {
            totalRecords = list.size();
        }
        return (int) Math.ceil((double) totalRecords / 10);
    }

    // 4. Total page Total page when searching in group of locked/nonlock users
    private int getSearchMaxPageLocked(String searchBy, String value, boolean isLock) {
        List<Users> list = null;
        switch (searchBy) {
            case "name":
                list = usersRepository
                        .findByFirstNameContainingOrLastNameContainingAndNonLocked(value, value, isLock);
                break;
            case "email":
                list = usersRepository.findByEmailContainingAndNonLocked(value, isLock);
                break;
            case "phone":
                list = usersRepository.findByPhoneContainingAndNonLocked(value, isLock);
                break;
        }
        int totalRecords = 0;
        if (list != null) {
            totalRecords = list.size();
        }
        return (int) Math.ceil((double) totalRecords / 10);
    }
}
