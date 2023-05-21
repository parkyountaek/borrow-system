package com.borrow.system.modulecore.domain.user;

import com.borrow.system.modulecore.domain.audit.UpdateBaseEntity;
import com.borrow.system.modulecore.domain.organization.Organization;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Entity
@Getter
@Table(name = "BR_USER")
@AllArgsConstructor
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
    @Column(name = "PHONE_NUMBER", nullable = false)
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE", nullable = false)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "ORGANIZATION_ID")
    private Organization organization;

    private User(String email, String name, String password, Organization organization, String phoneNumber, Role role) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.organization = organization;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.initCreateBaseEntity();
        this.initUpdateBaseEntity();
    }

    public static User user(String email, String name, String password, Organization organization, String phoneNumber) {
        return new User(email, name, password, organization, phoneNumber, Role.USER);
    }

    public static User admin(String email, String name, String password, String phoneNumber) {
        return new User(email, name, password, null, phoneNumber, Role.ADMIN);
    }

    public void updateProperty(User user) {
        this.name = Objects.isNull(user.getEmail()) ? this.name : user.getName();
        this.password = Objects.isNull(user.getPassword()) ? this.password : user.getPassword();
        this.organization = Objects.isNull(user.getOrganization()) ? this.organization : user.getOrganization();
        this.phoneNumber = Objects.isNull(user.getPhoneNumber()) ? this.phoneNumber : user.getPhoneNumber();
    }
}
