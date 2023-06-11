package com.borrow.system.appitem.appborrow.application.port.out;

import com.borrow.system.modulecore.domain.borrow.Borrow;

import java.util.Optional;

public interface LoadPort {
    Optional<Borrow> findByUserId(Long userId);
    Optional<Borrow> findByItemId(Long itemId);
    Optional<Borrow> findById(Long id);
}
