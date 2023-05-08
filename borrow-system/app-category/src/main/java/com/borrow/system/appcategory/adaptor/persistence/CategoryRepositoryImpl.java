package com.borrow.system.appcategory.adaptor.persistence;

import com.borrow.system.modulecore.category.domain.Category;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public class CategoryRepositoryCustomImpl implements CategoryRepositoryCustom {
    private final JpaQueryFactory
    @Override
    public List<Category> findAllByUserId(Long userId, Sort createdAt) {
        return null;
    }

    @Override
    public Optional<Category> findByIdAndUserId(Long id, Long userId) {
        return Optional.empty();
    }
}
