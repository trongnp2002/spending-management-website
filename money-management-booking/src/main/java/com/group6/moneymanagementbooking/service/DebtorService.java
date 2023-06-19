package com.group6.moneymanagementbooking.service;

import java.util.List;
import java.util.Optional;

import com.group6.moneymanagementbooking.enity.Debtor;

public interface DebtorService {

    public List<Debtor> findAll(int id);

    public void Save(Debtor debtor);

    public List<Debtor> SearchByName(String name);

    public Optional<Debtor> getDebtor(int id);

    public void deleteDebtorById(int id);

}
