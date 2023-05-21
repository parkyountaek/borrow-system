package com.borrow.system.appcategory.adaptor.persistence;

import com.borrow.system.modulecore.domain.category.Category;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.borrow.system.modulecore.domain.category.QCategory.*;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Category> findAllByOrganizationId(Long organizationId) {
        return this.jpaQueryFactory.select(category)
                .from(category)
                .where(category.organization.id.eq(organizationId))
                .orderBy(category.createdAt.desc())
                .fetch();
    }

    @Override
    public Optional<Category> findByIdAndOrganizationId(Long id, Long organizationId) {
        return Optional.ofNullable(this.jpaQueryFactory.select(category)
                .from(category)
                .where(category.organization.id.eq(organizationId)
                        .and(category.id.eq(id))
                )
                .fetchOne());
    }
}