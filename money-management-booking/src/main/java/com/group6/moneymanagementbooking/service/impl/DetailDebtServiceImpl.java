package com.group6.moneymanagementbooking.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.group6.moneymanagementbooking.enity.Debt_detail;
import com.group6.moneymanagementbooking.enity.Debtor;
import com.group6.moneymanagementbooking.repository.DetailDebtRepository;
import com.group6.moneymanagementbooking.service.DetailDebtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DetailDebtServiceImpl implements DetailDebtService {
   private final DetailDebtRepository detailDebtRepository;

   @Override
   public List<Debt_detail> findAll() {
      return detailDebtRepository.findAll();
   }

   @Override
   public List<Debt_detail> findAllById(int ids) {

      return detailDebtRepository.findAllByDeptorId(ids);
   }

   @Override
   public Debt_detail Save(Debt_detail detail_edbt) {
      return detailDebtRepository.save(detail_edbt);
   }

   @Override
   public void deleteDebtById(int id) {
      detailDebtRepository.deleteById(id);
   }

   @Override
   public Debt_detail findById(int id) {
      Optional<Debt_detail> debt = detailDebtRepository.findById(id);
      return debt.get();
   }

}
