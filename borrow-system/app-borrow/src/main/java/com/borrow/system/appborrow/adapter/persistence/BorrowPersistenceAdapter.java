package com.borrow.system.appborrow.adapter.persistence;

import com.borrow.system.appborrow.application.port.in.DeletePort;
import com.borrow.system.appborrow.application.port.in.SavePort;
import com.borrow.system.appborrow.application.port.out.LoadPort;
import com.borrow.system.modulecore.domain.borrow.Borrow;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BorrowPersistenceAdapter implements SavePort, LoadPort, DeletePort {
    private final BorrowRepository borrowRepository;

    @Override
    public Borrow saveBorrow(Borrow borrow) {
        return borrowRepository.save(borrow);
    }

    @Override
    public Optional<Borrow> findByUserId(Long userId) {
        return borrowRepository.findByUserId(userId);
    }

    @Override
    public Optional<Borrow> findByItemId(Long itemId) {
        return borrowRepository.findByItemId(itemId);
    }

    @Override
    public Optional<Borrow> findById(Long id) {
        return borrowRepository.findById(id);
    }

    @Override
    public void deleteBorrow(Borrow borrow) {
        borrowRepository.delete(borrow);
    }
}
