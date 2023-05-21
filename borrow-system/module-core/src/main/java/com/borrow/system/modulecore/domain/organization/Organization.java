package com.borrow.system.modulecore.domain.organization;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Entity
@Getter
@AllArgsConstructor
@Table(name = "BR_ORGANIZATION")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "DETAIL_ADDRESS")
    private String detailAddress;
    @Column(name = "REPRESENTATIVE_NUMBER")
    private String representativeNumber;
    @Column(name = "FAX_NUMBER")
    private String faxNumber;

    public static Organization of(String name, String address, String detailAddress, String representativeNumber, String faxNumber) {
        return new Organization(null, name, address, detailAddress, representativeNumber, faxNumber);
    }

    public void updateProperty(Organization organization) {
        this.address = Objects.isNull(organization.getAddress()) ? this.address : organization.getAddress();
        this.detailAddress = Objects.isNull(organization.getDetailAddress()) ? this.detailAddress : organization.getDetailAddress();
        this.representativeNumber = Objects.isNull(organization.getRepresentativeNumber()) ? this.representativeNumber : organization.getRepresentativeNumber();
        this.faxNumber = Objects.isNull(organization.getFaxNumber()) ? this.faxNumber : organization.getFaxNumber();
    }
}
