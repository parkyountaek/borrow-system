package com.borrow.system.appusermanagement.application.service;

import com.borrow.system.appusermanagement.application.port.in.SavePort;
import com.borrow.system.appusermanagement.application.port.out.LoadPort;
import com.borrow.system.modulecommon.exception.BusinessLogicException;
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
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    UserService userService;
    @Mock
    SavePort savePort;
    @Mock
    LoadPort loadPort;

    @BeforeEach
    void beforeEach() {
        userService = new UserService(savePort, loadPort);
    }


    @Nested
    @DisplayName("email 로 회원이 있는지 확인한다.")
    class ExistUserByEmailTest {
        @Test
        @DisplayName("존재한다.")
        void existTest() {
            // given
            String email = "email";
            User user = User.user(email, "name", "password", null, "phoneNumber");
            given(loadPort.findByEmail(email))
                    .willReturn(Optional.of(user));

            // when & then
            assertThatThrownBy(() -> userService.existUser(email))
                    .isInstanceOf(BusinessLogicException.class)
                    .hasMessage("USER_ALREADY_EXIST");
        }

        @Test
        @DisplayName("존재하지 않는다.")
        void notExistTest() {
            // given
            String email = "no_email";
            given(loadPort.findByEmail(email))
                    .willReturn(Optional.empty());

            // when
            userService.existUser(email);

            // then
            then(loadPort)
                    .should()
                    .findByEmail(email);
        }
    }

    @Test
    @DisplayName("회원을 등록한다.")
    void createUserTest() {
        // given
        User user = User.user("email", "name", "password", null, "phoneNumber");
        given(savePort.saveUser(user))
                .willReturn(user);
        // when
        User saveUser = userService.createUser(user);

        // then
        then(savePort)
                .should()
                .saveUser(user);

        assertThat(saveUser).isEqualTo(user);
    }

    @Test
    @DisplayName("회원 정보를 업데이트 한다.")
    void updateUserTest() {
        // given
        User user = User.user("email", "name", "password", null, "phoneNumber");
        User updateUser = User.user("email", "changeName", null, null, "changePhoneNumber");
        given(loadPort.findByEmail(user.getEmail()))
                .willReturn(Optional.of(user));
        user.updateProperty(updateUser);
        given(savePort.saveUser(user))
                .willReturn(user);
        // when
        User updatedUser = userService.updateUser(updateUser);

        // then
        then(loadPort)
                .should()
                .findByEmail(user.getEmail());
        then(savePort)
                .should()
                .saveUser(user);

        assertThat(updatedUser.getName()).isEqualTo(updateUser.getName());
        assertThat(updatedUser.getOrganization()).isEqualTo(updateUser.getOrganization());
        assertThat(updatedUser.getPhoneNumber()).isEqualTo(updateUser.getPhoneNumber());
    }

    @Test
    @DisplayName("이메일로 회원을 조회한다.")
    void getUserByEmailTest() {
        // given
        String email = "email";
        User user = User.user("email", "name", "password", null, "phoneNumber");
        given(loadPort.findByEmail(email))
                .willReturn(Optional.of(user));

        // when
        User findUser = userService.getUserByEmail(email);

        // then
        then(loadPort)
                .should()
                .findByEmail(email);

        assertThat(findUser).isEqualTo(user);
    }

    @Test
    @DisplayName("회원 아이디로 회원을 조회한다.")
    void getUserByIdTest() {
        // given
        Long id = 1L;
        User user = User.user("email", "name", "password", null, "phoneNumber");
        given(loadPort.findById(id))
                .willReturn(Optional.of(user));

        // when
        User findUser = userService.getUserById(id);

        // then
        then(loadPort)
                .should()
                .findById(id);

        assertThat(findUser).isEqualTo(user);
    }
}