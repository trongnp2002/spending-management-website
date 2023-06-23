package com.group6.moneymanagementbooking.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.group6.moneymanagementbooking.dto.response.UsersForAdminDTOResponse;

public interface AdminService {

  public List<UsersForAdminDTOResponse> getPageGroupOfUsers(Model model, int page);

  public void changeActiveStatus(HttpServletResponse response,int id) throws IOException;
 
 
  public List<UsersForAdminDTOResponse> searchUsers(Model model, String searchBy, String value, int page);

  public List<UsersForAdminDTOResponse>  getListOfLockedUser(Model model, Boolean islock, int page);

  public List<UsersForAdminDTOResponse>  getListOfUnActiveUser(Model model, Boolean isActive, int page);

  public List<UsersForAdminDTOResponse> searchListUsersActive(Model model, String searchBy, String value, int page, Boolean isactive);

  public List<UsersForAdminDTOResponse> searchListUsersNonLock(Model model, String searchBy, String value, int page, Boolean nonLock);



}
