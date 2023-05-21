package com.borrow.system.appcategory.adaptor.persistence;

import com.borrow.system.appcategory.config.TestConfig;
import com.borrow.system.apporganization.adapter.persistence.OrganizationRepository;
import com.borrow.system.modulecore.domain.category.Category;
import com.borrow.system.modulecore.domain.organization.Organization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(TestConfig.class)
@ActiveProfiles("test")
class CategoryRepositoryTest {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    OrganizationRepository organizationRepository;
    Organization organization;

    @BeforeEach
    void beforeEach() {
        Organization willSaveOrganization = Organization.of("name", "address", "detailAddress", "representativeNumber", "faxNumber");
        this.organization = this.organizationRepository.save(willSaveOrganization);
        Category category = Category.create(null, "name", 10, true, organization);
        this.categoryRepository.save(category);
    }

    @Test
    @DisplayName("Category 생성 테스트")
    void saveTest() {
        // given
        Category category = Category.create(null, "name", 10, true, this.organization);

        // when
        Category saveCategory = this.categoryRepository.save(category);

        // then
        assertThat(saveCategory).isEqualTo(category);
    }

    @Test
    @DisplayName("해당 조직의 카테고리들 조회")
    void findAllByUserIdTest() {
        // given
        Long organizationId = this.organization.getId();

        // when
        List<Category> categories = this.categoryRepository.findAllByOrganizationId(organizationId);

        // then
        assertThat(categories).hasSize(1);
    }

    @Test
    @DisplayName("해당 카테고리를 삭제한다.")
    void deleteCategoryTest() {
        // given
        Category category = Category.create(null, "name", 2, true, organization);
        Category savedCategory = categoryRepository.save(category);

        // when
        categoryRepository.delete(savedCategory);

        // then
        Optional<Category> findCategory = categoryRepository.findById(savedCategory.getId());
        assertThat(findCategory).isNotPresent();
    }
}