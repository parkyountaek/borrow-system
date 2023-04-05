package com.borrow.system.modulecore.comment.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "B_COMMENT")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "TEXT")
    private String text;
}
