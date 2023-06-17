package com.group6.moneymanagementbooking.enity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
@Setter
@Getter
@Builder
@Table(name = "debt_detail")

public class Debt_detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
   
    @Column(name = "debtor_id")
    private int deptorId;
    
    private int account_id;
    private String note;
    private double money_debt;
    private boolean classify;
     @Column(name = "date_debt")
    private LocalDateTime date_create;

}
