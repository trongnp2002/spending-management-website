package com.group6.moneymanagementbooking.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.group6.moneymanagementbooking.enity.Accounts;
import com.group6.moneymanagementbooking.enity.Debt_detail;
import com.group6.moneymanagementbooking.enity.Debtor;
import com.group6.moneymanagementbooking.repository.DetailDebtRepository;
import com.group6.moneymanagementbooking.service.AccountsService;
import com.group6.moneymanagementbooking.service.DebtorService;
import com.group6.moneymanagementbooking.service.DetailDebtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DetailDebtServiceImpl implements DetailDebtService {
   private final DetailDebtRepository detailDebtRepository;
   private final DebtorService debtorService;
   private final AccountsService accountsService;

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
      Accounts acc = accountsService.findById(detail_edbt.getAccount_id());
      Debtor depDebtor = (debtorService.getDebtor(detail_edbt.getDeptorId())).get();

      depDebtor.setTotal(detail_edbt.isClassify() ? (depDebtor.getTotal() + detail_edbt.getMoney_debt())
            : (depDebtor.getTotal() - detail_edbt.getMoney_debt()));

      acc.setBalance(detail_edbt.isClassify() ? (acc.getBalance() - detail_edbt.getMoney_debt())
            : (acc.getBalance() + detail_edbt.getMoney_debt()));

      depDebtor.setDate_update(LocalDateTime.now());
      return detailDebtRepository.save(detail_edbt);
   }

   @Override
   public void deleteDebtById(int id) {
      Debt_detail debt = (detailDebtRepository.findById(id)).get();
      Debtor debtor = debtorService.getDebtorById(debt.getDeptorId());
      Accounts accounts = accountsService.findById(debt.getAccount_id());
      double money_debt = debt.getMoney_debt();
      if (debt.isClassify()) {
         accounts.setBalance(accounts.getBalance() + money_debt);
         debtor.setTotal(debtor.getTotal() - money_debt);
      } else if (!debt.isClassify()) {
         accounts.setBalance(accounts.getBalance() - money_debt);
         debtor.setTotal(debtor.getTotal() + money_debt);
      }
      debtor.setDate_update(LocalDateTime.now());
      detailDebtRepository.deleteById(id);
   }

   @Override
   public Debt_detail findById(int id) {
      Optional<Debt_detail> debt = detailDebtRepository.findById(id);
      return debt.get();
   }

}
