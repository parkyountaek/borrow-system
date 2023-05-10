package com.borrow.system.appcategory.adaptor.persistence;

import com.borrow.system.modulecore.category.domain.Category;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.borrow.system.modulecore.category.domain.QCategory.*;

@Repository
public class CategoryRepositoryImpl implements CategoryRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    public CategoryRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<Category> findAllByUserId(Long userId) {
        return this.jpaQueryFactory.select(category)
                .from(category)
                .where(category.user.id.eq(userId))
                .orderBy(category.createdAt.desc())
                .fetch();
    }

    @Override
    public Optional<Category> findByIdAndUserId(Long id, Long userId) {
        return Optional.ofNullable(this.jpaQueryFactory.select(category)
                .from(category)
                .where(category.user.id.eq(userId)
                        .and(category.id.eq(id))
                )
                .fetchOne());
    }
}