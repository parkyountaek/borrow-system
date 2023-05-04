package com.borrow.system.modulecore.category.domain;

import com.borrow.system.modulecore.audit.CreateBaseEntity;
import jakarta.persistence.*;

@Entity
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

    public Category() {
    }

    public Category(Long id, String name, int borrowDay, boolean isQr) {
        this.id = id;
        this.name = name;
        this.borrowDay = borrowDay;
        this.isQr = isQr;
    }

    public static Category create(Long id, String name, int borrowDay, boolean isQr) {
        return new Category(id, name, borrowDay, isQr);
    }

    public Long getId() {
        return this.id;
    }
}
