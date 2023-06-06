package com.borrow.system.appusermanagement.adapter.persistence;

import com.borrow.system.modulecore.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class UserPersistenceAdapterTest {
    UserPersistenceAdapter userPersistenceAdapter;

    @Mock
    UserRepository userRepository;

    User user;
    @BeforeEach
    void beforeEach() {
        this.userPersistenceAdapter = new UserPersistenceAdapter(userRepository);
        this.user = User.user("email", "name", "password", null, "phoneNumber");
    }
    @Nested
    @DisplayName("회원 아이디로 회원을 조회한다.")
    class GetUserTest {
        @Test
        @DisplayName("성공 테스트")
        void successTest() {
            // given
            Long id = 1L;
            given(userRepository.findById(id))
                    .willReturn(Optional.of(user));

            // when
            Optional<User> findUser = userPersistenceAdapter.findById(id);

            // then
            then(userRepository)
                    .should()
                    .findById(id);
            assertThat(findUser).isPresent().get().isEqualTo(user);
        }

        @Test
        @DisplayName("실패 테스트")
        void failTest() {
            // given
            Long id = 1L;
            given(userRepository.findById(id))
                    .willReturn(Optional.empty());

            // when
            Optional<User> findUser = userPersistenceAdapter.findById(id);

            // then
            then(userRepository)
                    .should()
                    .findById(id);
            assertThat(findUser).isNotPresent();
        }
    }

    @Nested
    @DisplayName("이메일로 회원을 조회한다.")
    class GetUserByEmailTest {
        @Test
        @DisplayName("성공 테스트")
        void successTest() {
            // given
            String email = "email";
            given(userRepository.findByEmail(email))
                    .willReturn(Optional.of(user));

            // when
            Optional<User> findUser = userPersistenceAdapter.findByEmail(email);

            // then
            then(userRepository)
                    .should()
                    .findByEmail(email);
            assertThat(findUser).isPresent().get().isEqualTo(user);
        }

        @Test
        @DisplayName("실패 테스트")
        void failTest() {
            // given
            String email = "email";
            given(userRepository.findByEmail(email))
                    .willReturn(Optional.empty());

            // when
            Optional<User> findUser = userPersistenceAdapter.findByEmail(email);

            // then
            then(userRepository)
                    .should()
                    .findByEmail(email);
            assertThat(findUser).isNotPresent();
        }
    }

    @Test
    @DisplayName("회원을 등록한다.")
    void saveUserTest() {
        // given
        given(userRepository.save(user))
                .willReturn(user);

        // when
        User createUser = userPersistenceAdapter.saveUser(user);

        // then
        assertThat(createUser).isEqualTo(user);
    }
}