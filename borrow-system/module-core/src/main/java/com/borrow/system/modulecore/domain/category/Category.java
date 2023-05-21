package com.borrow.system.modulecore.domain.category;

import com.borrow.system.modulecore.domain.audit.CreateBaseEntity;
import com.borrow.system.modulecore.domain.organization.Organization;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

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
    private Integer borrowDay;
    @Column(name = "IS_QR")
    private Boolean isQr;
    @ManyToOne
    @JoinColumn(name = "ORGANIZATION_ID")
    private Organization organization;

    public static Category create(Long id, String name, Integer borrowDay, Boolean isQr, Organization organization) {
        return new Category(id, name, borrowDay, isQr, organization);
    }

    public void update(Category category) {
        this.name = Objects.isNull(category.getName()) ? this.name : category.getName();
        this.borrowDay = Objects.isNull(category.getBorrowDay()) ? this.borrowDay : category.getBorrowDay();
        this.isQr = Objects.isNull(category.getIsQr()) ? this.isQr : category.getIsQr();
    }
}
