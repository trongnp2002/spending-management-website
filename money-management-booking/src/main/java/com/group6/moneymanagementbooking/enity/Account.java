package com.group6.moneymanagementbooking.enity;

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
@AllArgsConstructor
@Setter
@Getter
@Builder
@Table(name = "dbo.Account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accountID")
    private int accountID;

    @Column(name = "accountName")
    private String accountName;

    @Column(name = "password")
    private String password;
    
    @Column(name ="accountEmail" , unique = true)
    private String accountEmail;

    @Column(name = "accountAddress")
    private String accountAddress;

    @Column(name = "accountPhone")
    private String accountPhone;

    @Column(name = "isActive")
    private boolean is_Active;

    @Column(name = "isAdmin")
    private boolean is_Admin;

}
