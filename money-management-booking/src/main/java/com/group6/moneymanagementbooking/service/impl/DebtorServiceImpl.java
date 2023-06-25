package com.group6.moneymanagementbooking.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.group6.moneymanagementbooking.enity.Debtor;
import com.group6.moneymanagementbooking.repository.DebtorRepository;
import com.group6.moneymanagementbooking.service.DebtorService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DebtorServiceImpl implements DebtorService {

  private final DebtorRepository debtorRepository;

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
  public List<Debtor> SearchByName(String name, int userid) {
    return debtorRepository.findAllByNameContainingAnduserid(userid, name);
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

}
