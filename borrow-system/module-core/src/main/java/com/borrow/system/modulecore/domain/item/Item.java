package com.borrow.system.modulecore.domain.item;

import com.borrow.system.modulecore.domain.audit.UpdateBaseEntity;
import com.borrow.system.modulecore.domain.category.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

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
    private Boolean isBorrow;
    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    public Item() {}

    public void updateProperty(Item item) {
        this.name = Objects.isNull(item.getName()) ? this.name : item.getName();
        this.link = Objects.isNull(item.getLink()) ? this.link : item.getLink();
        this.description = Objects.isNull(item.getDescription()) ? this.description : item.getDescription();
        this.img = Objects.isNull(item.getImg()) ? this.img : item.getImg();
        this.qrImg = Objects.isNull(item.getQrImg()) ? this.qrImg : item.getQrImg();
        this.isBorrow = Objects.isNull(item.getIsBorrow()) ? this.isBorrow : item.getIsBorrow();
    }

    public void borrow() {
        this.isBorrow = true;
    }
}
