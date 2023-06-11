package com.borrow.system.appborrow.adapter.persistence;

import com.borrow.system.modulecore.domain.borrow.Borrow;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.borrow.system.modulecore.domain.borrow.QBorrow.*;

@Repository
@RequiredArgsConstructor
public class BorrowRepositoryImpl implements BorrowRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public Optional<Borrow> findByUserId(Long userId) {
        return Optional.ofNullable(jpaQueryFactory.select(borrow)
                .from(borrow)
                .where(borrow.user.id.eq(userId))
                .fetchOne());
    }

    @Override
    public Optional<Borrow> findByItemId(Long itemId) {
        return Optional.ofNullable(jpaQueryFactory.select(borrow)
                .from(borrow)
                .where(borrow.item.id.eq(itemId))
                .fetchOne());
    }
}
