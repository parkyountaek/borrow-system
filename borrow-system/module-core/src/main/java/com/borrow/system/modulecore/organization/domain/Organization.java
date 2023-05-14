package com.borrow.system.modulecore.organization.domain;

import jakarta.persistence.*;

@Entity
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

    public Organization() {
    }

    public Organization(String name, String address, String detailAddress, String representativeNumber, String faxNumber) {
        this.name = name;
        this.address = address;
        this.detailAddress = detailAddress;
        this.representativeNumber = representativeNumber;
        this.faxNumber = faxNumber;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public String getRepresentativeNumber() {
        return representativeNumber;
    }

    public String getFaxNumber() {
        return faxNumber;
    }
}
