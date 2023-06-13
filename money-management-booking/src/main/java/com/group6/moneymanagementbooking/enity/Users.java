package com.group6.moneymanagementbooking.enity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Validation;

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
@Table(name = "users")
public class Users extends Validation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String first_name;

    private String last_name;
    @Column(unique = true)

    private String email;

    @Column(unique = true)
    private String phone;

    private String address;

    private String password;

    private String token;

    private String role;

    private boolean is_active;

    private double annually_spending;

    private double monthly_spending;

    private double monthly_saving;

    private double monthly_earning;

    private String currency;
}
