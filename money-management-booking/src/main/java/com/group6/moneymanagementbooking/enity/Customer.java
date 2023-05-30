package com.group6.moneymanagementbooking.enity;

import javax.persistence.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Builder
public class User {
    private int id;
    private String name;
    private String address;
    private String password;
    private String token;
    private String email;
}
