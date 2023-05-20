package com.borrow.system.modulecore.category.domain;

import com.borrow.system.modulecore.audit.CreateBaseEntity;
import com.borrow.system.modulecore.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Entity
@Getter
@AllArgsConstructor
@Table(name = "B_CATEGORY")
public class Category extends CreateBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "BORROW_DAY")
    private int borrowDay;
    @Column(name = "IS_QR")
    private boolean isQr;
    @ManyToOne
    @JoinColumn(name = "CREATED_BY")
    private User user;

    public static Category create(Long id, String name, int borrowDay, boolean isQr, User user) {
        return new Category(id, name, borrowDay, isQr, user);
    }
}
