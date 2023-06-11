package com.borrow.system.appborrow.application;

import com.borrow.system.appborrow.application.port.in.DeletePort;
import com.borrow.system.appborrow.application.port.in.SavePort;
import com.borrow.system.appborrow.application.port.out.LoadPort;
import com.borrow.system.appborrow.config.TestConfig;
import com.borrow.system.modulecommon.exception.BusinessLogicException;
import com.borrow.system.modulecore.domain.borrow.Borrow;
import com.borrow.system.modulecore.domain.category.Category;
import com.borrow.system.modulecore.domain.item.Item;
import com.borrow.system.modulecore.domain.organization.Organization;
import com.borrow.system.modulecore.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ActiveProfiles("test")
@Import(TestConfig.class)
@ExtendWith(MockitoExtension.class)
class BorrowServiceTest {
    BorrowService borrowService;
    @Mock
    SavePort savePort;
    @Mock
    LoadPort loadPort;
    @Mock
    DeletePort deletePort;

    Item item;
    User user;
    Organization organization;
    Category category;

    @BeforeEach
    void beforeEach() {
        borrowService = new BorrowService(savePort, loadPort, deletePort);
        organization = new Organization(1L, "name", "address", "detailAddress", "representativeNumber", "faxNumber");
        user = User.user("email", "name", "passowrd", organization, "phoneNumber");
        category = new Category(1L, "name", 7, true, organization);
        item = new Item(1L, "name", "link", "description", "img", "qrImg", false, category);
    }

    @Nested
    @DisplayName("빌린 데이터를 저장하는 로직 테스트")
    class SaveBorrowTest {
        @Test
        @DisplayName("성공 테스트")
        void successTest() {
            // given
            Borrow borrow = new Borrow(null, LocalDateTime.now(), LocalDateTime.now().plusDays(7L), user, item);
            given(loadPort.findByItemId(borrow.getItem().getId()))
                    .willReturn(Optional.empty());
            given(savePort.saveBorrow(borrow))
                    .willReturn(borrow);

            // when
            Borrow savedBorrow = borrowService.saveBorrow(borrow);

            // then
            then(loadPort)
                    .should()
                    .findByItemId(borrow.getItem().getId());
            then(savePort)
                    .should()
                    .saveBorrow(borrow);
            assertThat(savedBorrow).isEqualTo(borrow);
        }

        @Test
        @DisplayName("실패 테스트")
        void failTest() {
            // given
            Borrow borrow = new Borrow(null, LocalDateTime.now(), LocalDateTime.now().plusDays(7L), user, item);
            given(loadPort.findByItemId(borrow.getItem().getId()))
                    .willReturn(Optional.of(borrow));

            // when & then
            assertThatThrownBy(() -> borrowService.saveBorrow(borrow))
                    .isInstanceOf(BusinessLogicException.class)
                    .hasMessage("ITEM_ALREADY_BORROW");
        }
    }

    @Nested
    @DisplayName("회원 아이디로 빌린 데이터를 조회한다.")
    class GetByUserIdTest {
        @Test
        @DisplayName("성공 테스트")
        void successTest() {
            // given
            Long userId = 1L;
            Borrow borrow = new Borrow(null, LocalDateTime.now(), LocalDateTime.now().plusDays(7L), user, item);
            given(loadPort.findByUserId(userId))
                    .willReturn(Optional.of(borrow));

            // when
            Borrow findBorrow = borrowService.getByUserId(userId);

            // then
            then(loadPort)
                    .should()
                    .findByUserId(userId);
            assertThat(findBorrow).isEqualTo(borrow);
        }

        @Test
        @DisplayName("실패 테스트")
        void failTest() {
            // given
            Long userId = 1L;
            given(loadPort.findByUserId(userId))
                    .willReturn(Optional.empty());

            // when & then
            assertThatThrownBy(() -> borrowService.getByUserId(userId))
                    .isInstanceOf(BusinessLogicException.class)
                    .hasMessage("NOT_BORROW_YET");
        }
    }

    @Nested
    @DisplayName("아니템 아이디로 빌린 데이터를 조회하는 로직 테스트")
    class GetByItemIdTest {
        @Test
        @DisplayName("성공 테스트")
        void successTest() {
            // given
            Long itemId = 1L;
            Borrow borrow = new Borrow(null, LocalDateTime.now(), LocalDateTime.now().plusDays(7L), user, item);
            given(loadPort.findByItemId(itemId))
                    .willReturn(Optional.of(borrow));

            // when
            Borrow findBorrow = borrowService.getByItemId(itemId);

            // then
            then(loadPort)
                    .should()
                    .findByItemId(itemId);
            assertThat(findBorrow).isEqualTo(borrow);
        }

        @Test
        @DisplayName("실패 테스트")
        void failTest() {
            // given
            Long itemId = 1L;
            given(loadPort.findByItemId(itemId))
                    .willReturn(Optional.empty());

            // when & then
            assertThatThrownBy(() -> borrowService.getByItemId(itemId))
                    .isInstanceOf(BusinessLogicException.class)
                    .hasMessage("NOT_BORROW_YET");
        }
    }

    @Nested
    @DisplayName("빌린 데이터 아이디로 빌린 데이터를 조회한다.")
    class GetByIdTest {
        @Test
        @DisplayName("성공 테스트")
        void successTest() {
            // given
            Long borrowId = 1L;
            Borrow borrow = new Borrow(borrowId, LocalDateTime.now(), LocalDateTime.now().plusDays(7L), user, item);
            given(loadPort.findById(borrowId))
                    .willReturn(Optional.of(borrow));

            // when
            Borrow findBorrow = borrowService.getById(borrowId);

            // then
            then(loadPort)
                    .should()
                    .findById(borrowId);
            assertThat(findBorrow).isEqualTo(borrow);
        }

        @Test
        @DisplayName("실패 테스트")
        void failTest() {
            // given
            Long borrowId = 1L;
            given(loadPort.findById(borrowId))
                    .willReturn(Optional.empty());

            // when & then
            assertThatThrownBy(() -> borrowService.getById(borrowId))
                    .isInstanceOf(BusinessLogicException.class)
                    .hasMessage("BORROW_NOT_FOUND");
        }
    }

    @Nested
    @DisplayName("빌린 데이터를 삭제하는 로직 테스트")
    class DeleteBorrowTest {
        @Test
        @DisplayName("성공 테스트")
        void successTest() {
            // given
            Long borrowId = 1L;
            Borrow borrow = new Borrow(borrowId, LocalDateTime.now(), LocalDateTime.now().plusDays(7L), user, item);
            given(loadPort.findById(borrowId))
                    .willReturn(Optional.of(borrow));

            // when
            borrowService.deleteBorrow(borrow);

            // then
            then(loadPort)
                    .should()
                    .findById(borrowId);
        }

        @Test
        @DisplayName("실패 테스트")
        void failTest() {
            // given
            Long borrowId = 1L;
            Borrow borrow = new Borrow(borrowId, LocalDateTime.now(), LocalDateTime.now().plusDays(7L), user, item);
            given(loadPort.findById(borrowId))
                    .willReturn(Optional.empty());

            // when & then
            assertThatThrownBy(() -> borrowService.deleteBorrow(borrow))
                    .isInstanceOf(BusinessLogicException.class)
                    .hasMessage("BORROW_NOT_FOUND");
        }
    }
}