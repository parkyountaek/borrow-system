package com.borrow.system.modulecore.borrow.domain;

import com.borrow.system.modulecore.audit.UpdateBaseEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "B_BORROW")
public class Borrow extends UpdateBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "START_BORROW_DTM")
    private LocalDateTime startBorrowDtm;
    @Column(name = "END_BORROW_DTM")
    private LocalDateTime endBorrowDtm;
}
