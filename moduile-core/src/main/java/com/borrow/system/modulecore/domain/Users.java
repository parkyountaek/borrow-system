package com.borrow.system.modulecore.domain;

import com.borrow.system.modulecore.audit.ModifiedTimeEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "USERS")
public class Users extends ModifiedTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", columnDefinition = "bigint")
    private Long id;
    @Column(name = "EMAIL", columnDefinition = "varchar(20)", unique = true)
    private String email;
    @Column(name = "PASSWORD", columnDefinition = "varchar(80)")
    private String password;
    @Column(name = "NAME", columnDefinition = "varchar(20)", nullable = false)
    private String name;
    @Column(name = "ROLE", columnDefinition = "varchar(20)", nullable = false)
    private String role;

    public Users() {
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }
}
