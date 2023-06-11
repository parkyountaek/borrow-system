package com.borrow.system.appborrow.adapter.persistence;

import com.borrow.system.modulecore.domain.borrow.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRepository extends JpaRepository<Borrow, Long>, BorrowRepositoryCustom {
}
