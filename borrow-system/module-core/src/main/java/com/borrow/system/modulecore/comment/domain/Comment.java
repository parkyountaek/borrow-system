package com.borrow.system.modulecore.comment.domain;

import com.borrow.system.modulecore.item.domain.Item;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Entity
@Getter
@AllArgsConstructor
@Table(name = "B_COMMENT")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "TEXT")
    private String text;
    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;
}
