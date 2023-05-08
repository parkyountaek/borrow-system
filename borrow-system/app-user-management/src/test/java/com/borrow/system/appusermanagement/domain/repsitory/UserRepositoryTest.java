package com.borrow.system.appusermanagement.domain.repsitory;

import com.borrow.system.appusermanagement.persistence.UserRepository;
import com.borrow.system.modulecore.user.domain.Role;
import com.borrow.system.modulecore.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("회원을 저장한다.")
    void saveUserTest() {
        // given
        User user = new User(null, "email", "name", "password", "organization", "phoneNumber", Role.ADMIN);

        // when
        User saveUser = this.userRepository.save(user);

        // then
        assertThat(saveUser).isEqualTo(user);
    }

    @Test
    @DisplayName("회원을 아이디로 조회한다.")
    void findByIdTest() {
        // given
        User user = new User(null, "email", "name", "password", "organization", "phoneNumber", Role.ADMIN);
        User saveUser = this.userRepository.save(user);

        // when
        Optional<User> findUser = this.userRepository.findById(saveUser.getId());

        // then
        assertThat(findUser.isPresent()).isEqualTo(true);
        assertThat(findUser.get()).isEqualTo(saveUser);
    }
}