package com.borrow.system.appitem.adapter.persistence;

import com.borrow.system.modulecore.domain.category.Category;
import com.borrow.system.modulecore.domain.item.Item;
import com.borrow.system.modulecore.domain.organization.Organization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
class ItemPersistenceAdapterTest {
    ItemPersistenceAdapter itemPersistenceAdapter;
    @Mock
    ItemRepository itemRepository;
    Organization organization;
    Category category;

    @BeforeEach
    void beforeEach() {
        itemPersistenceAdapter = new ItemPersistenceAdapter(itemRepository);
        organization = new Organization(1L, "name", "address", "detailAddress", "representativeNumber", "faxNumber");
        category = new Category(1L, "name", 1, true, this.organization);
    }

    @Test
    @DisplayName("아이템을 저장한다.")
    void saveItem() {
        // given
        Item item = new Item(null, "name", "link", "description", "img", "qrImg", true, category);
        given(itemRepository.save(item))
                .willReturn(item);

        // when
        Item saveItem = itemPersistenceAdapter.saveItem(item);

        // then
        then(itemRepository)
                .should()
                .save(item);
        assertThat(saveItem).isEqualTo(item);
    }

    @Test
    @DisplayName("카테고리 아이디와 조직 아이디로 아이템들을 조회한다.")
    void findByCategoryIdAndOrganizationId() {
        // given
        List<Item> items = List.of(new Item(null, "name", "link", "description", "img", "qrImg", true, category));
        given(itemRepository.findByCategoryIdAndOrganizationId(category.getId(), category.getOrganization().getId()))
                .willReturn(items);

        // when
        List<Item> findItems = itemPersistenceAdapter.findByCategoryIdAndOrganizationId(category.getId(), category.getOrganization().getId());

        // then
        then(itemRepository)
                .should()
                .findByCategoryIdAndOrganizationId(category.getId(), category.getOrganization().getId());
        assertThat(findItems).hasSize(1);
    }

    @Test
    @DisplayName("아이템의 아이디와 카테고리 아이디, 조직 아이디로 아이템을 찾는다.")
    void findByIdAndCategoryIdAndOrganizationId() {
        // given
        Item item = new Item(1L, "name", "link", "description", "img", "qrImg", true, category);
        given(itemRepository.findByIdAndCategoryIdAndOrganizationId(item.getId(), item.getCategory().getId(), item.getCategory().getOrganization().getId()))
                .willReturn(Optional.of(item));

        // when
        Optional<Item> findItem = itemPersistenceAdapter.findByIdAndCategoryIdAndOrganizationId(item.getId(), item.getCategory().getId(), item.getCategory().getOrganization().getId());

        // then
        then(itemRepository)
                .should()
                .findByIdAndCategoryIdAndOrganizationId(item.getId(), item.getCategory().getId(), item.getCategory().getOrganization().getId());
        assertThat(findItem).isPresent().get().isEqualTo(item);
    }

    @Test
    @DisplayName("아이템을 삭제한다.")
    void deleteItem() {
        // given
        Item item = new Item(1L, "name", "link", "description", "img", "qrImg", true, category);
        doNothing().when(itemRepository)
                .delete(item);

        // when
        itemPersistenceAdapter.deleteItem(item);

        // then
        then(itemRepository)
                .should()
                .delete(item);
    }
}