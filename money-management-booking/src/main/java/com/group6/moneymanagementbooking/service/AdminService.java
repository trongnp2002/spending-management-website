package com.group6.moneymanagementbooking.service;

import org.springframework.ui.Model;


public interface AdminService {

      public String getPageGroupOfUsers(Model model, int page);

      public String changeActiveStatus(int id, String page);

      public String changeActiveStatus(int id, String page, String searchBy, String value);

      public String searchUsers(Model model, String searchBy, String value, int page);

    public String getListOfLockedUser(Model model, Boolean islock, int page);

    public String getListOfUnActiveUser(Model model, Boolean isActive, int page);

    public String searchListUsersActive(Model model, String searchBy, String value, int page, Boolean isactive);

    public String searchListUsersNonLock(Model model, String searchBy, String value, int page, Boolean isLock);

    public String changeActiveStatusActivePage(Model model, int id, int page, String searchBy, String value, Boolean isActive);

    public String changeActiveStatusActivePage(Model model, int id, int page, Boolean isActive);

    public String changeActiveStatusNonLocked(Model model, int id, int page, String searchBy, String value, Boolean isActive);

    public String changeActiveStatusNonLocked(Model model, int id, int page, Boolean isActive);

}
