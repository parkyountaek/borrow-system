package com.borrow.system.appborrow.adapter.persistence;

import com.borrow.system.modulecore.domain.borrow.Borrow;

import java.util.Optional;

public interface BorrowRepositoryCustom {
    Optional<Borrow> findByUserId(Long userId);
    Optional<Borrow> findByItemId(Long itemId);
}
