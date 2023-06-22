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
    public String getPageGroupOfUsers(Model model, int page) {
        int totalPage = getMaxPage();
        if (page > totalPage)
            page = totalPage;
        if (totalPage == 0)
            page = 1;
        List<Users> list = usersRepository.findAll(PageRequest.of(page - 1, PAGE_SIZE)).toList();
        return WebUtils.dispartcher(HOME, model, toUsersForAdminDTOResponses(list), page,
                totalPage, false, false, false, false);
    }

    @Override
    public String changeActiveStatus(int id, String page) {
        updateStatus(id);
        return "redirect:/admins/home/" + page;
    }

    @Override
    public String changeActiveStatus(int id, String page, String searchBy, String value) {
        updateStatus(id);
        return "redirect:/admins/search/" + page + "/?select=" + searchBy + "&value=" + value;
    }

    @Override
    public String changeActiveStatusActivePage(Model model, int id, int page, String searchBy, String value,
            Boolean isActive) {
        updateStatus(id);
        return searchListUsersActive(model, searchBy, value, page, isActive);
    }

    @Override
    public String changeActiveStatusActivePage(Model model, int id, int page, Boolean isActive) {
        updateStatus(id);
        return getListOfUnActiveUser(model, isActive, page);
    }

    @Override
    public String changeActiveStatusNonLocked(Model model, int id, int page, String searchBy, String value,
            Boolean isActive) {
        updateStatus(id);
        return searchListUsersNonLock(model, searchBy, value, page, isActive);
    }

    @Override
    public String changeActiveStatusNonLocked(Model model, int id, int page, Boolean isActive) {
        updateStatus(id);
        return getListOfLockedUser(model, isActive, page);
    }

    private void updateStatus(int id) {
        Optional<Users> userOptional = usersRepository.findById(id);
        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            user.setActive(!user.isActive());
            usersRepository.save(user);
        }
    }

    public int getMaxPage() {
        long totalRecords = usersRepository.count();
        int maxPage = (int) Math.ceil((double) totalRecords / PAGE_SIZE);
        return maxPage;
    }

    @Override
    public String searchUsers(Model model, String searchBy, String value, int page) {
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
        return WebUtils.dispartcher(HOME, model, toUsersForAdminDTOResponses(groupOfUsers), page + 1, totalPage, false,
                false, false, false);
    }

    @Override
    public String getListOfLockedUser(Model model, Boolean islock, int page) {
        int totalPage = getSearchMaxPage("locked", islock.toString());
        if (page >= totalPage)
            page = totalPage;
        if (totalPage == 0)
            page = 1;
        List<Users> listOfLockedUser = usersRepository.findByNonLocked(islock, PageRequest.of((page - 1), PAGE_SIZE))
                .toList();
        boolean isLockPage = true;
        return WebUtils.dispartcher(HOME, model, toUsersForAdminDTOResponses(listOfLockedUser), page, totalPage,
                !isLockPage, isLockPage, !isLockPage, !islock);
    }

    @Override
    public String getListOfUnActiveUser(Model model, Boolean isActive, int page) {
        int totalPage = getSearchMaxPage("active", isActive.toString());
        boolean isActivePage = true;
        if (page >= totalPage) {
            page = totalPage;
        }
        if (totalPage == 0)
            page = 1;
        List<Users> listOfLockedUser = usersRepository.findByActive(isActive, PageRequest.of((page - 1), PAGE_SIZE))
                .toList();
        return WebUtils.dispartcher(HOME, model, toUsersForAdminDTOResponses(listOfLockedUser), page, totalPage,
                isActivePage, !isActivePage, !isActive, !isActivePage);
    }

    @Override
    public String searchListUsersNonLock(Model model, String searchBy, String value, int page, Boolean isLock) {
        List<Users> groupOfUsers = null;
        boolean isLockedPage = true;
        isLock = !isLock;
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
        isLock = !isLock;
        model.addAttribute("txtSearch", value);
        model.addAttribute("select", searchBy);
        return WebUtils.dispartcher(HOME, model, toUsersForAdminDTOResponses(groupOfUsers), page + 1, totalPage,
                !isLockedPage, isLockedPage, !isLockedPage, isLock);
    }

    @Override
    public String searchListUsersActive(Model model, String searchBy, String value, int page, Boolean isActive) {
        List<Users> groupOfUsers = null;
        boolean isActivePage = true;
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
        isActive = !isActive;
        model.addAttribute("txtSearch", value);
        model.addAttribute("select", searchBy);
        return WebUtils.dispartcher(HOME, model, toUsersForAdminDTOResponses(groupOfUsers), page + 1, totalPage,
                isActivePage, !isActivePage, isActive, !isActivePage);
    }

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

    private List<UsersForAdminDTOResponse> toUsersForAdminDTOResponses(List<Users> users) {
        List<UsersForAdminDTOResponse> list = new ArrayList<UsersForAdminDTOResponse>();
        for (Users user : users) {
            if (user.getRole().equals("ROLE_USER")) {
                list.add(UsersMapper.toUsersForAdminDTOResponse(user));
            }
        }
        return list;
    }

}
