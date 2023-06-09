package com.group6.moneymanagementbooking.enity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Validation;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

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
@Table(name = "Accounts")
public class Account extends Validation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Name cannot be empty")
    private String name;

    @NotNull(message = "Password cannot be emtpy")
    private String password;
    @Column(unique = true)

    @NotNull(message = "Email cannot be empty")
    @Email(message = "Email is invalid")
    private String email;

    private String address;

    @Column(unique = true)
    @NotNull(message = "Phone cannot be empty")
    private String phone;

    private boolean is_Active;

    private String roll;
}
