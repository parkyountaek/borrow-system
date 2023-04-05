package com.borrow.system.modulecore.item.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "B_ITEM")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "LINK")
    private String link;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "IMG")
    private String img;
    @Column(name = "QR_IMG")
    private String qrImg;
    @Column(name = "IS_BORROW")
    private boolean isBorrow;
}
