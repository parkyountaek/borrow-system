package com.borrow.system.modulecore.user.domain;

import com.borrow.system.modulecore.audit.UpdateBaseEntity;
import jakarta.persistence.*;

import java.util.Objects;

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

    private User(String email, String name, String password, String organization, String phoneNumber, Role role) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.organization = organization;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.initCreateBaseEntity();
        this.initUpdateBaseEntity();
    }

    public static User user(String email, String name, String password, String organization, String phoneNumber) {
        return new User(email, name, password, organization, phoneNumber, Role.USER);
    }

    public static User admin(String email, String name, String password, String organization, String phoneNumber) {
        return new User(email, name, password, organization, phoneNumber, Role.ADMIN);
    }

    public void updateProperty(User user) {
        this.name = Objects.isNull(user.getEmail()) ? this.name : user.getName();
        this.password = Objects.isNull(user.getPassword()) ? this.organization : user.getPassword();
        this.organization = Objects.isNull(user.getOrganization()) ? this.organization : user.getOrganization();
        this.phoneNumber = Objects.isNull(user.getPhoneNumber()) ? this.phoneNumber : user.getPhoneNumber();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getOrganization() {
        return organization;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
