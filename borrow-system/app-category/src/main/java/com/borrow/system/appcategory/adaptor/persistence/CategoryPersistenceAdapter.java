package com.borrow.system.appcategory.adaptor.persistence;

import com.borrow.system.modulecore.category.domain.Category;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CategoryPersistenceAdapter {
    private final CategoryRepository categoryRepository;

    public CategoryPersistenceAdapter(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(Category category) {
        return this.categoryRepository.save(category);
    }

    public List<Category> findAllByUserId(Long userId) {
        return this.categoryRepository.findAllByUserId(userId);
    }

    public Optional<Category> findByIdAndUserId(Long id, Long userId) {
        return this.categoryRepository.findByIdAndUserId(id, userId);
    }

    public void deleteCategory(Category category) {
        this.categoryRepository.delete(category);
    }
}
