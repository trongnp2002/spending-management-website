package com.group6.moneymanagementbooking.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.group6.moneymanagementbooking.enity.Debtor;
import com.group6.moneymanagementbooking.enity.Users;
import com.group6.moneymanagementbooking.repository.DebtorRepository;
import com.group6.moneymanagementbooking.service.DebtorService;
import com.group6.moneymanagementbooking.service.UsersService;
import com.group6.moneymanagementbooking.util.SecurityUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DebtorServiceImpl implements DebtorService {

  private final DebtorRepository debtorRepository;
  private final UsersService usersService;

  @Override
  public List<Debtor> findAll(int id) {
    return debtorRepository.findAllByUserid(id);
  }

  @Override
  public Debtor Save(Debtor debtor) {
    debtor.setTotal(0.0);
    debtor.setDate_create(LocalDateTime.now());
    debtor.setDate_update(LocalDateTime.now());
    return debtorRepository.save(debtor);
  }

  @Override
  public List<Debtor> SearchByName(String name) {
    // List<Debtor> searchResults = new ArrayList<>();

    // if (currentReques.equals("/Debtor/ListAll")) {

    // searchResults = findAll(getIdUser());
    // } else if (currentReques.equals("/Debtor/ListOwner")) {

    // searchResults = getListOwner();
    // } else if (currentReques.equals("/Debtor/ListDebtor")) {

    // searchResults = getListDebtor();
    // }
    // List<Debtor> newli = new ArrayList<>();
    // for (Debtor item : searchResults) {
    // if (item.getName().toLowerCase().contains(name.toLowerCase().trim())) {
    // newli.add(item);
    // } else if (name == " ") {
    // newli.add(item);
    // }
    // }
    return debtorRepository.findAllByNameContainingAnduserid(getIdUser(), name);
  }

  @Override
  public Optional<Debtor> getDebtor(int id) {
    return debtorRepository.findById(id);
  }

  @Override
  public void deleteDebtorById(int id) {
    debtorRepository.deleteById(id);
  }

  @Override
  public Debtor getDebtorById(int id) {
    return (debtorRepository.findById(id)).get();
  }

  @Override
  public Debtor Update(Debtor debtor) {
    Optional<Debtor> debtors = getDebtor(debtor.getId());
    debtor.setDate_create(debtors.get().getDate_create());
    debtor.setDate_update(LocalDateTime.now());
    debtor.setTotal(debtors.get().getTotal());
    return debtorRepository.save(debtor);
  }

  private int getIdUser() {
    Users users = usersService.getUserByEmail(SecurityUtils.getCurrentUsername());
    return users.getId();
  }

  @Override
  public List<Debtor> getListOwner() {
    List<Debtor> listdeb = new ArrayList<>();
    for (var deb : findAll(getIdUser())) {
      if (deb.getTotal() < 0) {
        listdeb.add(deb);
      }
    }
    return listdeb;
  }

  @Override
  public List<Debtor> getListDebtor() {
    List<Debtor> listdeb = new ArrayList<>();
    for (var deb : findAll(getIdUser())) {
      if (deb.getTotal() > 0) {
        listdeb.add(deb);
      }
    }
    return listdeb;
  }

  @Override
  public List<Debtor> FilterDebtor(String filterType, String name, String filterValueStart, String filterValueEnd) {
    List<Debtor> listdebtor = new ArrayList<>();
    // if (name == "" && filterType.equals("total")) {
    // Double from = Double.parseDouble(filterValueStart);
    // Double to = Double.parseDouble(filterValueEnd);
    // listdebtor = debtorRepository.findAllByTotal(getIdUser(), from, to);
    // } else if (name == null && "date".equals(filterType)) {
    // String pattern = "yyyy-MM-dd";
    // DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
    // LocalDate dateStart = LocalDate.parse(filterValueStart, dateFormatter);
    // LocalDate dateEnd = LocalDate.parse(filterValueEnd, dateFormatter);
    // LocalDateTime dateTimeStart = dateStart.atStartOfDay();
    // LocalDateTime dateTimeEnd = dateEnd.atStartOfDay();
    // listdebtor = debtorRepository.findAllByDate(getIdUser(), dateTimeStart,
    // dateTimeEnd);
    // }
    if (name == null) {
      name = "";
    }

    if ("total".equals(filterType)) {
      if (filterValueStart != null && filterValueEnd != null) {
        try {
          Double from = Double.parseDouble(filterValueStart.trim());
          Double to = Double.parseDouble(filterValueEnd.trim());
          listdebtor = debtorRepository.findAllByTotal(getIdUser(), from, to);
        } catch (NumberFormatException e) {
          // Xử lý ngoại lệ khi không thể chuyển đổi thành số
          e.printStackTrace();
        }
      }
    } else if ("date".equals(filterType)) {
      if (filterValueStart != null && filterValueEnd != null) {
        try {
          String pattern = "yyyy-MM-dd";
          DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
          LocalDate dateStart = LocalDate.parse(filterValueStart.trim(), dateFormatter);
          LocalDate dateEnd = LocalDate.parse(filterValueEnd.trim(), dateFormatter);
          LocalDateTime dateTimeStart = dateStart.atStartOfDay();
          LocalDateTime dateTimeEnd = dateEnd.atStartOfDay();
          listdebtor = debtorRepository.findAllByDate(getIdUser(), dateTimeStart, dateTimeEnd);
        } catch (DateTimeParseException e) {
          // Xử lý ngoại lệ khi không thể chuyển đổi thành ngày tháng
          e.printStackTrace();
        }
      }
    }

    return listdebtor;
  }
}
