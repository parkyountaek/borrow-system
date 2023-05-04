package com.borrow.system.appcategory.adaptor.persistence;

import com.borrow.system.modulecore.category.domain.Category;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByUserId(Long userId, Sort createdAt);

    Optional<Category> findByIdAndUserId(Long id, Long userId);
}
