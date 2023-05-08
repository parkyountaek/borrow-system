package com.borrow.system.appcategory.adaptor.persistence;

import com.borrow.system.appusermanagement.persistence.UserRepository;
import com.borrow.system.modulecore.category.domain.Category;
import com.borrow.system.modulecore.user.domain.Role;
import com.borrow.system.modulecore.user.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class CategoryRepositoryTest {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    UserRepository userRepository;
    User user;

    @BeforeEach
    void beforeEach() {
        User willSaveUser = new User(null, "email", "name", "password", "organization", "phoneNumber", Role.USER);
        this.user = this.userRepository.save(willSaveUser);
        Category category = Category.create(null, "name", 10, true, this.user);
        this.categoryRepository.save(category);
    }

    @Test
    @DisplayName("Category 생성 테스트")
    void saveTest() {
        // given
        Category category = Category.create(null, "name", 10, true, user);

        // when
        Category saveCategory = this.categoryRepository.save(category);

        // then
        assertThat(saveCategory).isEqualTo(category);
    }

    @Test
    @DisplayName("해당 회원이 등록한 카테고리들 조회")
    void findAllByUserIdTest() {
        // given
        Long userId = this.user.getId();

        // when
        List<Category> categories = this.categoryRepository.findAllByUserId(userId);

        // then
        assertThat(categories.size()).isEqualTo(1);
    }
}