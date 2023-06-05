package com.borrow.system.appitem.adapter.persistence;

import com.borrow.system.modulecore.domain.item.Item;
import com.borrow.system.modulecore.domain.item.QItem;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.borrow.system.modulecore.domain.item.QItem.*;

@Repository
@RequiredArgsConstructor
public class ItemRepositoryImpl implements ItemRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Item> findByCategoryIdAndOrganizationId(Long categoryId, Long organizationId) {
        return jpaQueryFactory.select(item)
                .from(item)
                .where(item.category.id.eq(categoryId)
                        .and(item.category.organization.id.eq(organizationId)))
                .fetch();
    }

    @Override
    public Optional<Item> findByIdAndCategoryIdAndOrganizationId(Long id, Long categoryId, Long organizationId) {
        return Optional.ofNullable(jpaQueryFactory.select(item)
                .from(item)
                .where(item.id.eq(id)
                        .and(item.category.id.eq(categoryId))
                        .and(item.category.organization.id.eq(organizationId)))
                .fetchOne());
    }
}
