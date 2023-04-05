package com.borrow.system.modulecore.comment.domain.repository;

import com.borrow.system.modulecore.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
