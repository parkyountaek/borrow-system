package com.borrow.system.appcategory.adaptor.persistence;

import com.borrow.system.modulecore.domain.category.Category;
import com.borrow.system.modulecore.domain.organization.Organization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doNothing;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class CategoryPersistenceAdapterTest {
    @Autowired
    CategoryPersistenceAdapter categoryPersistenceAdapter;
    @Mock
    CategoryRepository categoryRepository;

    @BeforeEach
    void beforeEach() {
        categoryPersistenceAdapter = new CategoryPersistenceAdapter(categoryRepository);
    }

    @Test
    @DisplayName("카테고리를 생성한다.")
    void createCategoryTest() {
        // given
        Organization organization = Organization.of("name", "address", "detailAddress", "representativeNumber", "faxNumber");
        Category category = Category.create(1L, "name", 1, true, organization);
        given(categoryRepository.save(category))
                .willReturn(category);

        // when
        Category savedCategory = categoryPersistenceAdapter.saveCategory(category);

        // then
        then(categoryRepository)
                .should()
                .save(category);
        assertThat(savedCategory).isEqualTo(category);
    }

    @Test
    @DisplayName("조직 아이디로 등록된 모든 카테고리들을 조회한다.")
    void getAllByOrganizationIdTest() {
        // given
        Long organizationId = 1L;
        Organization organization = new Organization(organizationId, "name", "address", "detailAddress", "representativeNumber", "faxNumber");
        List<Category> categories = List.of(Category.create(1L, "name", 2, true, organization));
        given(categoryRepository.findAllByOrganizationId(organizationId))
                .willReturn(categories);

        // when
        List<Category> findCategories = categoryPersistenceAdapter.getAllByOrganizationId(organizationId);

        // then
        then(categoryRepository)
                .should()
                .findAllByOrganizationId(organizationId);
        assertThat(findCategories).isEqualTo(categories);
    }

    @Nested
    @DisplayName("카테고리 아이디와 조직 아이디로 카테고리를 조회한다.")
    class getByIdAndOrganizationIdTest {
        @Test
        @DisplayName("성공 테스트")
        void successTest() {
            // given
            Long id = 1L, organizationId = 1L;
            Organization organization = new Organization(organizationId, "name", "address", "detailAddress", "representativeNumber", "faxNumber");
            Category category = Category.create(1L, "name", 2, true, organization);
            given(categoryRepository.findByIdAndOrganizationId(id, organizationId))
                    .willReturn(Optional.of(category));

            // when
            Optional<Category> findCategory = categoryPersistenceAdapter.findByIdAndOrganizationId(id, organizationId);

            // then
            then(categoryRepository)
                    .should()
                    .findByIdAndOrganizationId(id, organizationId);
            assertThat(findCategory).isPresent().get().isEqualTo(category);
        }
    }


    @Test
    @DisplayName("카테고리를 삭제한다.")
    void deleteCategoryTest() {
        // given
        Organization organization = new Organization(1L, "name", "address", "detailAddress", "representativeNumber", "faxNumber");
        Category category = Category.create(1L, "name", 2, true, organization);
        doNothing()
                .when(categoryRepository)
                .delete(category);

        // when
        categoryPersistenceAdapter.deleteCategory(category);

        // then
        then(categoryRepository)
                .should()
                .delete(category);
    }
}