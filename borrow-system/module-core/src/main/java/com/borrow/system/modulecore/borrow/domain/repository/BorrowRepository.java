package com.borrow.system.modulecore.borrow.domain.repository;

import com.borrow.system.modulecore.borrow.domain.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {
}
