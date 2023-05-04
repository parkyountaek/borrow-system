package com.borrow.system.appcategory.adaptor.persistence;

import com.borrow.system.modulecore.category.domain.Category;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryPersistenceAdapter {
    private final CategoryRepository categoryRepository;

    public CategoryPersistenceAdapter(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(Category category) {
        return this.categoryRepository.save(category);
    }

    public List<Category> findAllByUserId(Long userId) {
        return this.categoryRepository.findAllByUserId(userId, Sort.by("CREATED_AT").ascending());
    }

    public Optional<Category> findByIdAndUserId(Long id, Long userId) {
        return this.categoryRepository.findByIdAndUserId(id, userId);
    }

    public void deleteCategory(Category category) {
        this.categoryRepository.delete(category);
    }
}
