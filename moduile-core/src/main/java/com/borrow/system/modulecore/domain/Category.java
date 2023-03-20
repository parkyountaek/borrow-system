package com.borrow.system.modulecore.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "CATEGORY")
public class Category {
    @Id
    @GeneratedValue
    @Column(name = "CATEGORY_ID", columnDefinition = "bigint")
    private Long id;
    @Column(name = "NAME", columnDefinition = "varchar(40)", nullable = false)
    private String name;
    @Column(name = "IS_QR", columnDefinition = "boolean default false", nullable = false)
    private boolean isQr;
    public Category() {
    }

}
