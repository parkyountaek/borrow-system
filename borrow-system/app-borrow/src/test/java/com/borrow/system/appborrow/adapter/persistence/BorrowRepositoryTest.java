package com.borrow.system.appborrow.adapter.persistence;

import com.borrow.system.appborrow.config.TestConfig;
import com.borrow.system.appcategory.adaptor.persistence.CategoryRepository;
import com.borrow.system.appitem.adapter.persistence.ItemRepository;
import com.borrow.system.apporganization.adapter.persistence.OrganizationRepository;
import com.borrow.system.appusermanagement.adapter.persistence.UserRepository;
import com.borrow.system.modulecore.domain.borrow.Borrow;
import com.borrow.system.modulecore.domain.category.Category;
import com.borrow.system.modulecore.domain.item.Item;
import com.borrow.system.modulecore.domain.organization.Organization;
import com.borrow.system.modulecore.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@Import(TestConfig.class)
@ActiveProfiles("test")
class BorrowRepositoryTest {
    @Autowired
    BorrowRepository borrowRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    OrganizationRepository organizationRepository;
    @Autowired
    CategoryRepository categoryRepository;

    Item savedItem;
    User savedUser;
    Organization savedOrganization;
    Category savedCategory;

    @BeforeEach
    void beforeEach() {
        Organization organization = new Organization(null, "name", "address", "detailAddress", "representativeNumber", "faxNumber");
        savedOrganization = organizationRepository.save(organization);
        User user = User.user("email", "name", "passowrd", savedOrganization, "phoneNumber");
        savedUser = userRepository.save(user);
        Category category = new Category(null, "name", 7, true, savedOrganization);
        savedCategory = categoryRepository.save(category);
        Item item = new Item(null, "name", "link", "description", "img", "qrImg", false, savedCategory);
        savedItem = itemRepository.save(item);
    }

    @Test
    @DisplayName("빌린 데이터를 저장한다.")
    void saveBorrowTest() {
        // given
        Borrow borrow = new Borrow(null, LocalDateTime.now(), LocalDateTime.now().plusDays(7L), savedUser, savedItem);

        // when
        Borrow savedBorrow = borrowRepository.save(borrow);

        // then
        assertThat(savedBorrow).isEqualTo(borrow);
    }

    @Test
    @DisplayName("회원 아이디로 빌린 데이터를 조회한다.")
    void findByUserIdTest() {
        // given
        Long userId = savedUser.getId();
        Borrow borrow = new Borrow(null, LocalDateTime.now(), LocalDateTime.now().plusDays(7L), savedUser, savedItem);
        Borrow savedBorrow = borrowRepository.save(borrow);

        // when
        Optional<Borrow> optionalBorrow = borrowRepository.findByUserId(userId);

        // then
        assertThat(optionalBorrow).isPresent().get().isEqualTo(savedBorrow);
    }

    @Test
    @DisplayName("아이템 아이디로 빌린 데이터를 조회한다.")
    void findByItemIdTest() {
        // given
        Long itemId = savedItem.getId();
        Borrow borrow = new Borrow(null, LocalDateTime.now(), LocalDateTime.now().plusDays(7L), savedUser, savedItem);
        Borrow savedBorrow = borrowRepository.save(borrow);

        // when
        Optional<Borrow> optionalBorrow = borrowRepository.findByItemId(itemId);

        // then
        assertThat(optionalBorrow).isPresent().get().isEqualTo(savedBorrow);
    }

    @Test
    @DisplayName("빌린 아이디로 빌린 데이터를 조회한다.")
    void findByIdTest() {
        // given
        Borrow borrow = new Borrow(null, LocalDateTime.now(), LocalDateTime.now().plusDays(7L), savedUser, savedItem);
        Borrow savedBorrow = borrowRepository.save(borrow);

        // when
        Optional<Borrow> optionalBorrow = borrowRepository.findByItemId(savedBorrow.getId());

        // then
        assertThat(optionalBorrow).isPresent().get().isEqualTo(savedBorrow);
    }

    @Test
    @DisplayName("빌린 데이터를 삭제한다.")
    void deleteBorrowTest() {
        // given
        Borrow borrow = new Borrow(null, LocalDateTime.now(), LocalDateTime.now().plusDays(7L), savedUser, savedItem);
        Borrow savedBorrow = borrowRepository.save(borrow);

        // when
        borrowRepository.delete(savedBorrow);

        // then
        Optional<Borrow> optionalBorrow = borrowRepository.findById(savedBorrow.getId());
        assertThat(optionalBorrow).isEmpty();
    }
}