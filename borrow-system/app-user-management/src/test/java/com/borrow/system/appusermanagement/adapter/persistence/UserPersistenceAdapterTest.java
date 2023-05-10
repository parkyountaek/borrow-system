package com.borrow.system.appusermanagement.adapter.persistence;

import com.borrow.system.modulecommon.exception.BusinessLogicException;
import com.borrow.system.modulecommon.exception.ExceptionCode;
import com.borrow.system.modulecore.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
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
        this.user = User.user("email", "name", "password", "organinzation", "phoneNumber");
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
            User findUser = userPersistenceAdapter.getUserById(id);

            // then
            then(userRepository)
                    .should()
                    .findById(id);
            assertThat(findUser).isEqualTo(user);
        }

        @Test
        @DisplayName("실패 테스트")
        void failTest() {
            // given
            Long id = 1L;
            given(userRepository.findById(id))
                    .willReturn(Optional.empty());

            // when & then
            assertThatThrownBy(() -> userPersistenceAdapter.getUserById(id))
                    .isInstanceOf(BusinessLogicException.class)
                    .message().isEqualTo(ExceptionCode.USER_NOT_FOUND.getMessage());

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
            User findUser = userPersistenceAdapter.getUserByEmail(email);

            // then
            then(userRepository)
                    .should()
                    .findByEmail(email);
            assertThat(findUser).isEqualTo(user);
        }

        @Test
        @DisplayName("실패 테스트")
        void failTest() {
            // given
            String email = "email";
            given(userRepository.findByEmail(email))
                    .willReturn(Optional.empty());

            // when & then
            assertThatThrownBy(() -> userPersistenceAdapter.getUserByEmail(email))
                    .isInstanceOf(BusinessLogicException.class)
                    .message().isEqualTo(ExceptionCode.USER_NOT_FOUND.getMessage());
        }
    }

    @Nested
    @DisplayName("회원을 등록한다.")
    class CreateUserTest {
        @Test
        @DisplayName("성공 테스트")
        void successTest() {
            // given
            given(userRepository.save(user))
                    .willReturn(user);

            // when
            User createUser = userPersistenceAdapter.createUser(user);

            // then
            assertThat(createUser).isEqualTo(user);
        }
    }
}