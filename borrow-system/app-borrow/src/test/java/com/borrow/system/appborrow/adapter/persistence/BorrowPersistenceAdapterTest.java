package com.borrow.system.appitem.appborrow.adapter.persistence;

import com.borrow.system.appitem.appborrow.config.TestConfig;
import com.borrow.system.modulecore.domain.borrow.Borrow;
import com.borrow.system.modulecore.domain.category.Category;
import com.borrow.system.modulecore.domain.item.Item;
import com.borrow.system.modulecore.domain.organization.Organization;
import com.borrow.system.modulecore.domain.user.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doNothing;

@ActiveProfiles("test")
@Import(TestConfig.class)
@ExtendWith(MockitoExtension.class)
class BorrowPersistenceAdapterTest {
    BorrowPersistenceAdapter borrowPersistenceAdapter;
    @Mock
    BorrowRepository borrowRepository;
    Organization organization;
    User user;
    Category category;
    Item item;
    @BeforeEach
    void beforeEach() {
        borrowPersistenceAdapter = new BorrowPersistenceAdapter(borrowRepository);
        organization = new Organization(1L, "name", "address", "detailAddress", "representativeNumber", "faxNumber");
        user = User.user("email", "name", "passowrd", organization, "phoneNumber");
        category = new Category(1L, "name", 7, true, organization);
        item = new Item(1L, "name", "link", "description", "img", "qrImg", false, category);
    }

    @Test
    @DisplayName("빌린 데이터를 저장한다.")
    void saveBorrowTest() {
        // given
        Borrow borrow = new Borrow(1L, LocalDateTime.now(), LocalDateTime.now().plusDays(7L), user, item);
        given(borrowRepository.save(borrow))
                .willReturn(borrow);

        // when
        Borrow savedBorrow = borrowPersistenceAdapter.saveBorrow(borrow);

        // then
        then(borrowRepository)
                .should()
                .save(borrow);
        assertThat(savedBorrow).isEqualTo(borrow);
    }

    @Test
    @DisplayName("회원 아이디로 빌린 데이터를 조회한다.")
    void findByUserIdTest() {
        // given
        Long userId = user.getId();
        Borrow borrow = new Borrow(1L, LocalDateTime.now(), LocalDateTime.now().plusDays(7L), user, item);
        given(borrowRepository.findByUserId(userId))
                .willReturn(Optional.of(borrow));

        // when
        Optional<Borrow> optionalBorrow = borrowPersistenceAdapter.findByUserId(userId);

        // then
        then(borrowRepository)
                .should()
                .findByUserId(userId);
        assertThat(optionalBorrow).isPresent().get().isEqualTo(borrow);
    }

    @Test
    @DisplayName("아이템 아이디로 빌린 데이터를 조회한다.")
    void findByItemIdTest() {
        // given
        Long itemId = item.getId();
        Borrow borrow = new Borrow(1L, LocalDateTime.now(), LocalDateTime.now().plusDays(7L), user, item);
        given(borrowRepository.findByItemId(itemId))
                .willReturn(Optional.of(borrow));

        // when
        Optional<Borrow> optionalBorrow = borrowRepository.findByItemId(itemId);

        // then
        then(borrowRepository)
                .should()
                .findByItemId(itemId);
        assertThat(optionalBorrow).isPresent().get().isEqualTo(borrow);
    }

    @Test
    @DisplayName("빌린 데이터 아이디로 벨린 데이터를 조회한다.")
    void findByIdTest() {
        // given
        Long borrowId = 1L;
        Borrow borrow = new Borrow(borrowId, LocalDateTime.now(), LocalDateTime.now().plusDays(7L), user, item);
        given(borrowRepository.findById(borrowId))
                .willReturn(Optional.of(borrow));

        // when
        Optional<Borrow> optionalBorrow = borrowRepository.findById(borrowId);

        // then
        then(borrowRepository)
                .should()
                .findById(borrowId);
        assertThat(optionalBorrow).isPresent().get().isEqualTo(borrow);
    }

    @Test
    @DisplayName("빌린 데이터를 삭제한다.")
    void deleteBorrowTest() {
        // given
        Borrow borrow = new Borrow(1L, LocalDateTime.now(), LocalDateTime.now().plusDays(7L), user, item);
        doNothing().when(borrowRepository)
                .delete(borrow);

        // when
        borrowPersistenceAdapter.deleteBorrow(borrow);

        // then
        then(borrowRepository)
                .should()
                .delete(borrow);
    }
}