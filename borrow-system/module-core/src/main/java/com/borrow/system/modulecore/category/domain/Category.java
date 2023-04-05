package com.borrow.system.modulecore.category.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "B_CATEGORY")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "BORROW_DAY")
    private int borrowDay;
    @Column(name = "IS_QR")
    private boolean isQr;
}
