package com.group6.moneymanagementbooking.service.impl;
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
  public void Save(Debtor debtor) {
    debtorRepository.save(debtor);
  }

  @Override
  public List<Debtor> SearchByName(String name) {
    return debtorRepository.findAllByNameContaining(name);
  }

  @Override
  public Optional<Debtor> getDebtor(int id) {
    return debtorRepository.findById(id);
  }

  @Override
  public void deleteDebtorById(int id) {
    debtorRepository.deleteById(id);
  }

}
