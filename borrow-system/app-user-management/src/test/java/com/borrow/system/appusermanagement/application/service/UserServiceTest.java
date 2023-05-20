package com.borrow.system.appusermanagement.application.service;

import com.borrow.system.appusermanagement.adapter.persistence.UserPersistenceAdapter;
import com.borrow.system.modulecore.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    UserService userService;
    @Mock
    UserPersistenceAdapter userPersistenceAdapter;

    @BeforeEach
    void beforeEach() {
        userService = new UserService(userPersistenceAdapter);
    }

    @Test
    @DisplayName("email 로 회원이 있는지 확인한다.")
    void existUserTest() {
        // given
        String email = "email";
        User.user(email, "name", "password", null, "phoneNumber");
        doNothing().when(userPersistenceAdapter)
                .existUserByEmail(email);

        // when
        userService.existUser(email);

        // then
        then(userPersistenceAdapter)
                .should()
                .existUserByEmail(email);
    }

    @Test
    @DisplayName("회원을 등록한다.")
    void createUserTest() {
        // given
        User user = User.user("email", "name", "password", null, "phoneNumber");
        given(userPersistenceAdapter.saveUser(user))
                .willReturn(user);
        // when
        User saveUser = userService.createUser(user);

        // then
        then(userPersistenceAdapter)
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
        given(userPersistenceAdapter.getUserByEmail(user.getEmail()))
                .willReturn(user);
        user.updateProperty(updateUser);
        given(userPersistenceAdapter.saveUser(user))
                .willReturn(user);
        // when
        User updatedUser = userService.updateUser(updateUser);

        // then
        then(userPersistenceAdapter)
                .should()
                .getUserByEmail(user.getEmail());

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
        given(userPersistenceAdapter.getUserByEmail(email))
                .willReturn(user);

        // when
        User findUser = userService.getUserByEmail(email);

        // then
        then(userPersistenceAdapter)
                .should()
                .getUserByEmail(email);

        assertThat(findUser).isEqualTo(user);
    }

    @Test
    @DisplayName("회원 아이디로 회원을 조회한다.")
    void getUserByIdTest() {
        // given
        Long id = 1L;
        User user = User.user("email", "name", "password", null, "phoneNumber");
        given(userPersistenceAdapter.getUserById(id))
                .willReturn(user);

        // when
        User findUser = userService.getUserById(id);

        // then
        then(userPersistenceAdapter)
                .should()
                .getUserById(id);

        assertThat(findUser).isEqualTo(user);
    }
}