package com.borrow.system.appcategory.adaptor.persistence;

import com.borrow.system.appcategory.application.port.in.DeletePort;
import com.borrow.system.appcategory.application.port.in.SavePort;
import com.borrow.system.appcategory.application.port.out.LoadPort;
import com.borrow.system.modulecore.domain.category.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CategoryPersistenceAdapter implements SavePort, LoadPort, DeletePort {
    private final CategoryRepository categoryRepository;

    @Override
    public Category saveCategory(Category category) {
        return this.categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Category category) {
        this.categoryRepository.delete(category);
    }

    @Override
    public List<Category> getAllByOrganizationId(Long organizationId) {
        return categoryRepository.findAllByOrganizationId(organizationId);
    }

    @Override
    public Optional<Category> findByIdAndOrganizationId(Long id, Long organizationId) {
        return categoryRepository.findByIdAndOrganizationId(id, organizationId);
    }
}