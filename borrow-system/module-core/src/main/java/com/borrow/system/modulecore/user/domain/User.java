package com.borrow.system.modulecore.user.domain;

import com.borrow.system.modulecore.audit.UpdateBaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "BR_USER")
public class User extends UpdateBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;
    @Column(name = "EMAIL", unique = true, nullable = false)
    private String email;
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "PASSWORD", nullable = false)
    private String password;
    @Column(name = "ORGANIZATION", nullable = false)
    private String organization;
    @Column(name = "PHONE_NUMBER", nullable = false)
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE", nullable = false)
    private Role role;

    public User(String email, String name, String password, String organization, String phoneNumber, Role role) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.organization = organization;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public Long getId() {
        return id;
    }
}
