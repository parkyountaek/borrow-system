package com.borrow.system.appcategory.application.service;

import com.borrow.system.appcategory.adaptor.persistence.CategoryPersistenceAdapter;
import com.borrow.system.modulecommon.exception.BusinessLogicException;
import com.borrow.system.modulecommon.exception.ExceptionCode;
import com.borrow.system.modulecore.domain.category.Category;
import com.borrow.system.modulecore.domain.organization.Organization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {
    CategoryService categoryService;
    @Mock
    CategoryPersistenceAdapter categoryPersistenceAdapter;

    @BeforeEach
    void beforeEach() {
        categoryService = new CategoryService(categoryPersistenceAdapter);
    }

    @Test
    @DisplayName("카테고리를 저장하는 로직 테스트")
    void storeCategoryTest() {
        // given
        Organization organization = new Organization(1L, "name", "address", "detailAddress", "representativeNumber", "faxNumber");
        Category category = Category.create(1L, "name", 2, true, organization);
        given(categoryPersistenceAdapter.saveCategory(category))
                .willReturn(category);

        // when
        Category savedCategory = categoryService.storeCategory(category);

        // then
        then(categoryPersistenceAdapter)
                .should()
                .saveCategory(category);
        assertThat(savedCategory).isEqualTo(category);
    }

    @Test
    @DisplayName("조직 아이디로 카테고리들을 조회하는 로직 테스트")
    void getAllByOrganizationIdTest() {
        // given
        Long organizationId = 1L;
        Organization organization = new Organization(organizationId, "name", "address", "detailAddress", "representativeNumber", "faxNumber");
        List<Category> categories = List.of(Category.create(1L, "name", 2, true, organization));
        given(categoryPersistenceAdapter.getAllByOrganizationId(organizationId))
                .willReturn(categories);

        // when
        List<Category> findCategories = categoryService.getAllByOrganizationId(organizationId);

        // then
        then(categoryPersistenceAdapter)
                .should()
                .getAllByOrganizationId(organizationId);
        assertThat(findCategories).isEqualTo(categories);
    }

    @Nested
    @DisplayName("카테고리 아이디와 조직 아이디로 카테고리를 조회하는 로직 테스트")
    class GetByIdAndOrganizationId {
        @Test
        @DisplayName("성공 테스트")
        void successTest() {
            // given
            Long id = 1L, organizationId = 1L;
            Organization organization = new Organization(organizationId, "name", "address", "detailAddress", "representativeNumber", "faxNumber");
            Category category = Category.create(id, "name", 2, true, organization);
            given(categoryPersistenceAdapter.getByIdAndOrganizationId(id, organizationId))
                    .willReturn(category);

            // when
            Category findCategory = categoryService.getByIdAndOrganizationId(id, organizationId);

            // then
            then(categoryPersistenceAdapter)
                    .should()
                    .getByIdAndOrganizationId(id, organizationId);
            assertThat(findCategory).isEqualTo(category);
        }

        @Test
        @DisplayName("실패 테스트")
        void failTest() {
            // given
            Long id = 1L, organizationId = 1L;
            doThrow(new BusinessLogicException(ExceptionCode.CATEGORY_NOT_FOUND))
                    .when(categoryPersistenceAdapter)
                    .getByIdAndOrganizationId(id, organizationId);

            // when & then
            assertThatThrownBy(() -> categoryService.getByIdAndOrganizationId(id, organizationId))
                    .isInstanceOf(BusinessLogicException.class)
                    .hasMessage("CATEGORY_NOT_FOUND");
        }
    }

    @Nested
    @DisplayName("카테고리 정보를 업데이트 하는 로직 테스트")
    class UpdateCategoryTest {
        @Test
        @DisplayName("성공 테스트")
        void successTest() {
            // given
            Long id = 1L, organizationId = 1L;
            Organization organization = new Organization(organizationId, "name", "address", "detailAddress", "representativeNumber", "faxNumber");
            Category category = Category.create(id, "name", 2, true, organization);
            Category updatingCategory = Category.create(id, "changeName", null, null, organization);
            given(categoryPersistenceAdapter.getByIdAndOrganizationId(id, organizationId))
                    .willReturn(category);
            category.update(updatingCategory);
            given(categoryPersistenceAdapter.saveCategory(category))
                    .willReturn(category);

            // when
            Category updatedCategory = categoryService.updateCategory(updatingCategory, organizationId);

            // then
            then(categoryPersistenceAdapter)
                    .should()
                    .getByIdAndOrganizationId(id, organizationId);
            then(categoryPersistenceAdapter)
                    .should()
                    .saveCategory(category);
            assertThat(updatedCategory).isEqualTo(category);
        }

        @Test
        @DisplayName("실패 테스트")
        void failTest() {
            // given
            Long id = 1L, organizationId = 1L;
            Organization organization = new Organization(organizationId, "name", "address", "detailAddress", "representativeNumber", "faxNumber");
            Category updatingCategory = Category.create(id, "changeName", null, null, organization);
            doThrow(new BusinessLogicException(ExceptionCode.CATEGORY_NOT_FOUND))
                    .when(categoryPersistenceAdapter)
                    .getByIdAndOrganizationId(id, organizationId);

            // when & then
            assertThatThrownBy(() -> categoryService.updateCategory(updatingCategory, organizationId))
                    .isInstanceOf(BusinessLogicException.class)
                    .hasMessage("CATEGORY_NOT_FOUND");
        }
    }

    @Nested
    @DisplayName("카테고리를 삭제하는 테스트")
    class DeleteCategoryTest {
        @Test
        @DisplayName("성공 테스트")
        void successTest() {
            Long id = 1L, organizationId = 1L;
            Organization organization = new Organization(organizationId, "name", "address", "detailAddress", "representativeNumber", "faxNumber");
            Category category = Category.create(id, "name", 2, true, organization);
            given(categoryPersistenceAdapter.getByIdAndOrganizationId(id, organizationId))
                    .willReturn(category);
            doNothing()
                    .when(categoryPersistenceAdapter)
                    .deleteCategory(category);

            // when
            categoryService.deleteCategory(id, organizationId);

            // then
            then(categoryPersistenceAdapter)
                    .should()
                    .getByIdAndOrganizationId(id, organizationId);
            then(categoryPersistenceAdapter)
                    .should()
                    .deleteCategory(category);
        }

        @Test
        @DisplayName("실패 테스트")
        void failTest() {
            // given
            Long id = 1L, organizationId = 1L;
            doThrow(new BusinessLogicException(ExceptionCode.CATEGORY_NOT_FOUND))
                    .when(categoryPersistenceAdapter)
                    .getByIdAndOrganizationId(id, organizationId);

            // when & then
            assertThatThrownBy(() -> categoryService.deleteCategory(id, organizationId))
                    .isInstanceOf(BusinessLogicException.class)
                    .hasMessage("CATEGORY_NOT_FOUND");
        }
    }
}