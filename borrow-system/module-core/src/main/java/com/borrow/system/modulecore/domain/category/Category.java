package com.borrow.system.modulecore.domain.category;

import com.borrow.system.modulecore.domain.audit.CreateBaseEntity;
import com.borrow.system.modulecore.domain.organization.Organization;
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
    @JoinColumn(name = "ORGANIZATION_ID")
    private Organization organization;

    public static Category create(Long id, String name, int borrowDay, boolean isQr, Organization organization) {
        return new Category(id, name, borrowDay, isQr, organization);
    }
}
