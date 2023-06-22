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
@Table(name = "debtor")
public class Debtor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id")
    private int userId;

    private String name;
    private String address;

    @Column(unique = true)
    private String phone;

    @Column(unique = true)
    private String email;
    private LocalDateTime date_create;
    private LocalDateTime date_update;
    private Double total;
}
