package com.borrow.system.modulecore.domain;

import com.borrow.system.modulecore.audit.ModifiedTimeEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "ITEM")
public class Item extends ModifiedTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_ID", columnDefinition = "bigint")
    private Long id;
    @Column(name = "NAME", columnDefinition = "varchar(30)", nullable = false)
    private String name;


}
