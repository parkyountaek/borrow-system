package com.borrow.system.modulecore.user.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "BR_USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "NAME")
    private String name;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "ORGANIZATION")
    private String organization;
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE")
    private Role role;
}
