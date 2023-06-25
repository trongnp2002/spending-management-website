package com.group6.moneymanagementbooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.group6.moneymanagementbooking.enity.Debtor;

@Repository
public interface DebtorRepository extends JpaRepository<Debtor, Integer> {
    public List<Debtor> findAllByNameContaining(String name);

    @Query("SELECT d FROM Debtor d WHERE d.userId = :userId")
    public List<Debtor> findAllByUserid(@Param("userId") int id);

    // @Query("SELECT d FROM Debtor d WHERE d.userId = :userId And d.name LIKE LIKE
    // '%?1%')
    @Query("SELECT d FROM Debtor d WHERE d.userId = ?1 AND d.name LIKE %?2%")
    public List<Debtor> findAllByNameContainingAnduserid(int id, String name);
}
