package com.borrow.system.appitem.adapter.persistence;

import com.borrow.system.appcategory.adaptor.persistence.CategoryRepository;
import com.borrow.system.appitem.config.TestConfig;
import com.borrow.system.apporganization.adapter.persistence.OrganizationRepository;
import com.borrow.system.modulecore.domain.category.Category;
import com.borrow.system.modulecore.domain.item.Item;
import com.borrow.system.modulecore.domain.organization.Organization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@Import(TestConfig.class)
class ItemRepositoryTest {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    OrganizationRepository organizationRepository;
    @Autowired
    CategoryRepository categoryRepository;
    Organization organization;
    Category category;

    @BeforeEach
    void beforeEach() {
        Organization savingOrganization = new Organization(null, "name", "address", "detailAddress", "representativeNumber", "faxNumber");
        organization = organizationRepository.save(savingOrganization);
        Category savigCategory = new Category(null, "name", 1, true, this.organization);
        category = categoryRepository.save(savigCategory);
    }
    @Test
    @DisplayName("아이템 저장 테스트")
    void saveTest() {
        // given
        Item item = new Item(null, "name", "link", "description", "img", "qrImg", true, null);

        // when
        Item saveItem = itemRepository.save(item);

        // then
        assertThat(saveItem).isEqualTo(item);
    }

    @Nested
    @DisplayName("아이템 조회 테스트")
    class FindItemTest {
        @Test
        @DisplayName("카테고리 아이디와 조직 아이디로 모든 아이템을 조회한다.")
        void findByCategoryIdAndOrganizationIdTest() {
            // given
            Item item = new Item(null, "name", "link", "description", "img", "qrImg", true, category);
            itemRepository.save(item);

            // when
            List<Item> items = itemRepository.findByCategoryIdAndOrganizationId(category.getId(), organization.getId());

            // then
            assertThat(items).hasSize(1);
        }

        @Test
        @DisplayName("아이템의 아이디와 카테고리 아이디, 조직 아이디로 아이템을 조회한다.")
        void findByIdAndCategoryIdAndOrganizationIdTest() {
            // given
            Item item = new Item(null, "name", "link", "description", "img", "qrImg", true, category);
            Item savedItem = itemRepository.save(item);

            // when
            Optional<Item> optionalItem = itemRepository.findByIdAndCategoryIdAndOrganizationId(savedItem.getId(), category.getId(), organization.getId());

            // then
            assertThat(optionalItem).isPresent().get().isEqualTo(savedItem);
        }
    }

    @Test
    @DisplayName("아이템을 삭제한다.")
    void deleteItemTest() {
        // given
        Item item = new Item(null, "name", "link", "description", "img", "qrImg", true, category);
        Item savedItem = itemRepository.save(item);

        // when
        itemRepository.delete(savedItem);

        // then
        Optional<Item> optionalItem = itemRepository.findByIdAndCategoryIdAndOrganizationId(savedItem.getId(), category.getId(), organization.getId());
        assertThat(optionalItem).isNotPresent();
    }
}