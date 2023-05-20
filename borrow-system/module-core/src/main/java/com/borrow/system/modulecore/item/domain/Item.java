package com.borrow.system.modulecore.item.domain;

import com.borrow.system.modulecore.audit.UpdateBaseEntity;
import com.borrow.system.modulecore.category.domain.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Entity
@Getter
@Table(name = "B_ITEM")
@AllArgsConstructor
public class Item extends UpdateBaseEntity {
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
    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;
}
