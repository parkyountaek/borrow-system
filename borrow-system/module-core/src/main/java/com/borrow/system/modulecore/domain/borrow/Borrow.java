package com.borrow.system.modulecore.domain.borrow;

import com.borrow.system.modulecore.domain.audit.UpdateBaseEntity;
import com.borrow.system.modulecore.domain.item.Item;
import com.borrow.system.modulecore.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@Table(name = "B_BORROW")
public class Borrow extends UpdateBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "START_BORROW_DTM")
    private LocalDateTime startBorrowDtm;
    @Column(name = "END_BORROW_DTM")
    private LocalDateTime endBorrowDtm;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;
}
