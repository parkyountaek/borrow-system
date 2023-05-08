package com.borrow.system.appcategory.adaptor.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
class CategoryPersistenceAdapterTest {
    @Autowired
    CategoryPersistenceAdapter categoryPersistenceAdapter;

    @Test
    void createCategoryTest() {
    }

    @Test
    void findAllByUserIdTest() {
    }

    @Test
    void findByIdAndUserIdTest() {
    }

    @Test
    void deleteCategoryTest() {
    }
}