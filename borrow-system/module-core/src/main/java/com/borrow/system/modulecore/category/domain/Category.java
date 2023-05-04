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

    public Category(String name, int borrowDay, boolean isQr) {
        this.name = name;
        this.borrowDay = borrowDay;
        this.isQr = isQr;
    }

    public static Category create(String name, int borrowDay, boolean isQr) {
        return new Category(name, borrowDay, isQr);
    }
}
