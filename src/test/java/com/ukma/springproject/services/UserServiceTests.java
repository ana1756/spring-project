package com.ukma.springproject.services;

import com.ukma.springproject.domain.Role;
import com.ukma.springproject.domain.User;
import com.ukma.springproject.repositories.UserRepository;
import com.ukma.springproject.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.TestPropertySource;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestPropertySource(properties = "spring.datasource.url=jdbc:h2:file:./src/main/resources/data/test-store")
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void clearDataSource() {
        userRepository.deleteAll();
    }

    @ParameterizedTest
    @MethodSource("provideUser")
    void saveUserTest(User user) {
        userService.save(user);
        assertEquals(user, userService.findByEmail(user.getEmail()));
    }

    @ParameterizedTest
    @MethodSource("provideUsersWithDuplicateEmails")
    void saveUsersWithDuplicateEmailsTest(List<User> users) {
        userService.save(users.get(0));
        assertThrows(DataIntegrityViolationException.class,
                () -> userService.save(users.get(1)));
    }

    @ParameterizedTest
    @MethodSource("provideUsersWithDuplicateUsernames")
    void saveUsersWithDuplicateUsernameTest(List<User> users) {
        userService.save(users.get(0));
        assertThrows(DataIntegrityViolationException.class,
                () -> userService.save(users.get(1)));
    }

    @ParameterizedTest
    @MethodSource("provideUser")
    void deleteUserTest(User user) {
        userService.save(user);
        userService.delete(user.getId());
        assertThrows(NoSuchElementException.class,
                () -> userService.findById(user.getId()));
    }

    @ParameterizedTest
    @MethodSource("provideUser")
    void updateUserTest(User user) {
        userService.save(user);

        user.setBalance(100.0);
        user.setAvatarName("new_avatar_name");
        userService.update(user);

        User storedUser = userRepository.findById(user.getId()).get();

        assertEquals(storedUser.getBalance(), user.getBalance());
        assertEquals(storedUser.getAvatarName(), user.getAvatarName());
    }

    private static Stream<User> provideUser() {
        User user = new User();
        user.setUsername("user1");
        user.setEmail("user1@email");
        user.setRole(Role.ROLE_USER);
        user.setBalance(0.0);
        user.setPassword("12345");
        user.setDateCreated(new Date());
        return Stream.of(user);
    }

    private static Stream<List<User>> provideUsersWithDuplicateEmails() {
        User user1 = new User();
        user1.setUsername("user1");
        user1.setEmail("user@email");
        user1.setRole(Role.ROLE_USER);
        user1.setBalance(0.0);
        user1.setPassword("12345");
        user1.setDateCreated(new Date());

        User user2 = new User();
        user2.setUsername("user2");
        user2.setEmail("user@email");
        user2.setRole(Role.ROLE_USER);
        user2.setBalance(0.0);
        user2.setPassword("12345");
        user2.setDateCreated(new Date());

        return Stream.of(List.of(user1, user2));
    }

    private static Stream<List<User>> provideUsersWithDuplicateUsernames() {
        User user1 = new User();
        user1.setUsername("user");
        user1.setEmail("user1@email");
        user1.setRole(Role.ROLE_USER);
        user1.setBalance(0.0);
        user1.setPassword("12345");
        user1.setDateCreated(new Date());

        User user2 = new User();
        user2.setUsername("user");
        user2.setEmail("user2@email");
        user2.setRole(Role.ROLE_USER);
        user2.setBalance(0.0);
        user2.setPassword("12345");
        user2.setDateCreated(new Date());

        return Stream.of(List.of(user1, user2));
    }

}
