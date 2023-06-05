package com.borrow.system.appitem.application.service;

import com.borrow.system.appitem.application.port.out.DeleteItemPort;
import com.borrow.system.appitem.application.port.out.LoadItemPort;
import com.borrow.system.appitem.application.port.out.SaveItemPort;
import com.borrow.system.modulecommon.exception.BusinessLogicException;
import com.borrow.system.modulecore.domain.category.Category;
import com.borrow.system.modulecore.domain.item.Item;
import com.borrow.system.modulecore.domain.organization.Organization;
import com.borrow.system.modulecore.domain.user.User;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {
    ItemService itemService;
    @Mock
    SaveItemPort saveItemPort;
    @Mock
    LoadItemPort loadItemPort;
    @Mock
    DeleteItemPort deleteItemPort;
    Organization organization;
    Category category;
    @BeforeEach
    void beforeEach() {
        itemService = new ItemService(saveItemPort, loadItemPort, deleteItemPort);
        organization = new Organization(1L, "name", "address", "detailAddress", "representativeNumber", "faxNumber");
        category = new Category(1L, "name", 1, true, this.organization);
    }

    @Nested
    @DisplayName("아이템 삭제 로직 테스트")
    class DeleteItemTest {
        @Test
        @DisplayName("성공 테스트")
        void successTest() {
            // given
            Item item = new Item(1L, "name", "link", "description", "img", "qrImg", true, category);
            given(loadItemPort.findByIdAndCategoryIdAndOrganizationId(item.getId(), item.getCategory().getId(), item.getCategory().getOrganization().getId()))
                    .willReturn(Optional.of(item));

            // when
            itemService.deleteItem(item);

            // then
            then(loadItemPort)
                    .should()
                    .findByIdAndCategoryIdAndOrganizationId(item.getId(), item.getCategory().getId(), item.getCategory().getOrganization().getId());
            then(deleteItemPort)
                    .should()
                    .deleteItem(item);
        }

        @Test
        @DisplayName("실패 테스트")
        void failTest() {
            Item item = new Item(1L, "name", "link", "description", "img", "qrImg", true, category);
            given(loadItemPort.findByIdAndCategoryIdAndOrganizationId(item.getId(), item.getCategory().getId(), item.getCategory().getOrganization().getId()))
                    .willReturn(Optional.empty());

            // when & then
            assertThatThrownBy(() -> itemService.deleteItem(item))
                            .isInstanceOf(BusinessLogicException.class)
                            .hasMessage("ITEM_NOT_FOUND");

            then(loadItemPort)
                    .should()
                    .findByIdAndCategoryIdAndOrganizationId(item.getId(), item.getCategory().getId(), item.getCategory().getOrganization().getId());
        }
    }

    @Test
    @DisplayName("아이템 등록 로직 테스트")
    void enrollItemTest() {
        // given
        Item item = new Item(1L, "name", "link", "description", "img", "qrImg", true, category);
        given(saveItemPort.saveItem(item))
                .willReturn(item);

        // when
        Item saveItem = itemService.enrollItem(item);

        // then
        then(saveItemPort)
                .should()
                .saveItem(item);
        assertThat(saveItem).isEqualTo(item);
    }

    @Test
    @DisplayName("아이템들 조회 로직 테스트")
    void getByCategoryIdAndOrganizationIdTest() {
        // given
        List<Item> items = List.of(new Item(null, "name", "link", "description", "img", "qrImg", true, category));
        given(loadItemPort.findByCategoryIdAndOrganizationId(category.getId(), organization.getId()))
                .willReturn(items);

        // when
        List<Item> findItems = itemService.getByCategoryIdAndOrganizationId(category.getId(), organization.getId());

        // then
        then(loadItemPort)
                .should()
                .findByCategoryIdAndOrganizationId(category.getId(), organization.getId());
        assertThat(findItems).hasSize(1);
    }

    @Nested
    @DisplayName("아이템 조회 로직 테스트")
    class GetByIdAndCategoryIdAndOrganizationIdTest {
        @Test
        @DisplayName("성공 테스트")
        void successTest() {
            // given
            Item item = new Item(1L, "name", "link", "description", "img", "qrImg", true, category);
            given(loadItemPort.findByIdAndCategoryIdAndOrganizationId(item.getId(), item.getCategory().getId(), item.getCategory().getOrganization().getId()))
                    .willReturn(Optional.of(item));

            // when
            Item findItem = itemService.getByIdAndCategoryIdAndOrganizationId(item.getId(), item.getCategory().getId(), item.getCategory().getOrganization().getId());

            // then
            then(loadItemPort)
                    .should()
                    .findByIdAndCategoryIdAndOrganizationId(item.getId(), item.getCategory().getId(), item.getCategory().getOrganization().getId());
            assertThat(findItem).isEqualTo(item);
        }

        @Test
        @DisplayName("실패 테스트")
        void failTest(){
            // given
            Long itemId = 1L;
            Long categoryId = 1L;
            Long organizationId = 1L;
            given(loadItemPort.findByIdAndCategoryIdAndOrganizationId(itemId, categoryId, organizationId))
                    .willReturn(Optional.empty());

            // when & then
            assertThatThrownBy(() -> itemService.getByIdAndCategoryIdAndOrganizationId(itemId, categoryId, organizationId))
                    .isInstanceOf(BusinessLogicException.class)
                    .hasMessage("ITEM_NOT_FOUND");

            then(loadItemPort)
                    .should()
                    .findByIdAndCategoryIdAndOrganizationId(itemId, categoryId, organizationId);

        }
    }

    @Nested
    @DisplayName("아이템 업데이트 로직 테스트")
    class UpdateItemTest {
        @Test
        @DisplayName("성공 테스트")
        void successTest() {
            // given
            Item item = new Item(1L, "name", "link", "description", "img", "qrImg", true, category);
            Item updateItem = new Item(1L, "changeName", null, null, null, null, null, category);
            given(loadItemPort.findByIdAndCategoryIdAndOrganizationId(item.getId(), item.getCategory().getId(), item.getCategory().getOrganization().getId()))
                    .willReturn(Optional.of(item));
            item.updateProperty(updateItem);
            given(saveItemPort.saveItem(item))
                    .willReturn(item);
            // when
            Item updatedItem = itemService.updateItem(updateItem);

            // then
            then(loadItemPort)
                    .should()
                    .findByIdAndCategoryIdAndOrganizationId(item.getId(), item.getCategory().getId(), item.getCategory().getOrganization().getId());
            then(saveItemPort)
                    .should()
                    .saveItem(item);
            assertThat(updatedItem).isEqualTo(item);
        }

        @Test
        @DisplayName("실패 테스트")
        void failTest() {
            // given
            Long itemId = 1L;
            Long categoryId = 1L;
            Long organizationId = 1L;
            Item updateItem = new Item(1L, "changeName", null, null, null, null, null, category);
            given(loadItemPort.findByIdAndCategoryIdAndOrganizationId(itemId, categoryId, organizationId))
                    .willReturn(Optional.empty());

            // when & then
            assertThatThrownBy(() -> itemService.updateItem(updateItem))
                    .isInstanceOf(BusinessLogicException.class)
                    .hasMessage("ITEM_NOT_FOUND");
        }
    }

    @Nested
    @DisplayName("아이템을 빌리는 로직 테스트.")
    class BorrowTest {
        @Test
        @DisplayName("성공 테스트")
        void successTest() {
            // given
            Item item = new Item(1L, "name", "link", "description", "img", "qrImg", false, category);
            User user = User.user("email", "name", "password", organization, "phoneNumber");
            given(loadItemPort.findByIdAndCategoryIdAndOrganizationId(item.getId(), item.getCategory().getId(), item.getCategory().getOrganization().getId()))
                    .willReturn(Optional.of(item));
            given(saveItemPort.saveItem(item))
                    .willReturn(item);
            // when
            Item borrowItem = itemService.borrowItem(item, user);

            // then
            then(loadItemPort)
                    .should()
                    .findByIdAndCategoryIdAndOrganizationId(item.getId(), item.getCategory().getId(), item.getCategory().getOrganization().getId());
            then(saveItemPort)
                    .should()
                    .saveItem(item);
            assertThat(borrowItem.getIsBorrow()).isTrue();
        }

        @Test
        @DisplayName("실패 테스트 - 이미 빌린 상태")
        void failTest_1() {
            // given
            Item item = new Item(1L, "name", "link", "description", "img", "qrImg", true, category);
            User user = User.user("email", "name", "password", organization, "phoneNumber");
            given(loadItemPort.findByIdAndCategoryIdAndOrganizationId(item.getId(), item.getCategory().getId(), item.getCategory().getOrganization().getId()))
                    .willReturn(Optional.of(item));

            // when & then
            assertThatThrownBy(() -> itemService.borrowItem(item, user))
                    .isInstanceOf(BusinessLogicException.class)
                    .hasMessage("ITEM_ALREADY_BORROW");

            then(loadItemPort)
                    .should()
                    .findByIdAndCategoryIdAndOrganizationId(item.getId(), item.getCategory().getId(), item.getCategory().getOrganization().getId());
        }

        @Test
        @DisplayName("실패 테스트")
        void failTest_2() {
            // given
            Item item = new Item(1L, "name", "link", "description", "img", "qrImg", false, category);
            Organization otherOrganization = new Organization(2L, "name", "address", "detailAddress", "representativeNumber", "faxNumber");
            User user = User.user("email", "name", "password", otherOrganization, "phoneNumber");
            given(loadItemPort.findByIdAndCategoryIdAndOrganizationId(item.getId(), item.getCategory().getId(), item.getCategory().getOrganization().getId()))
                    .willReturn(Optional.of(item));

            // when & then
            assertThatThrownBy(() -> itemService.borrowItem(item, user))
                    .isInstanceOf(BusinessLogicException.class)
                    .hasMessage("NO_PERMISSION");

            then(loadItemPort)
                    .should()
                    .findByIdAndCategoryIdAndOrganizationId(item.getId(), item.getCategory().getId(), item.getCategory().getOrganization().getId());
        }
    }
}