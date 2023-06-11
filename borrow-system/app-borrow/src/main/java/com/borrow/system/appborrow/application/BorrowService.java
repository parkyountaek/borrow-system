package com.borrow.system.appborrow.application;

import com.borrow.system.appborrow.application.port.in.DeleteBorrowUseCase;
import com.borrow.system.appborrow.application.port.in.DeletePort;
import com.borrow.system.appborrow.application.port.in.SaveBorrowUseCase;
import com.borrow.system.appborrow.application.port.in.SavePort;
import com.borrow.system.appborrow.application.port.out.LoadBorrowUseCase;
import com.borrow.system.appborrow.application.port.out.LoadPort;
import com.borrow.system.modulecommon.exception.BusinessLogicException;
import com.borrow.system.modulecommon.exception.ExceptionCode;
import com.borrow.system.modulecore.domain.borrow.Borrow;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BorrowService implements SaveBorrowUseCase, LoadBorrowUseCase, DeleteBorrowUseCase {
    private final SavePort savePort;
    private final LoadPort loadPort;
    private final DeletePort deletePort;

    @Override
    public Borrow saveBorrow(Borrow borrow) {
        loadPort.findByItemId(borrow.getItem().getId())
            .ifPresent(borrowElement -> {
                throw new BusinessLogicException(ExceptionCode.ITEM_ALREADY_BORROW);
            });
        return savePort.saveBorrow(borrow);
    }

    @Override
    @Transactional(readOnly = true)
    public Borrow getByUserId(Long userId) {
        return loadPort.findByUserId(userId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_BORROW_YET));
    }

    @Override
    @Transactional(readOnly = true)
    public Borrow getByItemId(Long itemId) {
        return loadPort.findByItemId(itemId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_BORROW_YET));
    }

    @Override
    public Borrow getById(Long id) {
        return loadPort.findById(id)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.BORROW_NOT_FOUND));
    }

    @Override
    public void deleteBorrow(Borrow borrow) {
        Borrow findBorrow = getById(borrow.getId());
        deletePort.deleteBorrow(findBorrow);
    }
}
