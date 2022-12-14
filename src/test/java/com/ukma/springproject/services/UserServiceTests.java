package com.ukma.springproject.services;

import com.ukma.springproject.domain.Role;
import com.ukma.springproject.domain.User;
import com.ukma.springproject.domain.dto.UserDTO;
import com.ukma.springproject.repositories.ApplicationRepository;
import com.ukma.springproject.repositories.UserRepository;
import com.ukma.springproject.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.modelmapper.ModelMapper;
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
class UserServiceTests {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private ModelMapper mapper;

    @BeforeEach
    void clearDataSource() {
        applicationRepository.deleteAll();
        userRepository.deleteAll();
    }

    @ParameterizedTest
    @MethodSource("provideUser")
    void saveUserTest(User user) {
        userService.save(mapper.map(user, UserDTO.class));
        assertEquals(mapper.map(user, UserDTO.class).getEmail(), userService.findByEmail(user.getEmail()).getEmail());
    }

    @ParameterizedTest
    @MethodSource("provideUsersWithDuplicateEmails")
    void saveUsersWithDuplicateEmailsTest(List<User> users) {
        userService.save(mapper.map(users.get(0), UserDTO.class));
        assertThrows(DataIntegrityViolationException.class,
                () -> userService.save(mapper.map(users.get(1), UserDTO.class)));
    }

    @ParameterizedTest
    @MethodSource("provideUsersWithDuplicateUsernames")
    void saveUsersWithDuplicateUsernameTest(List<User> users) {
        userService.save(mapper.map(users.get(0), UserDTO.class));
        assertThrows(DataIntegrityViolationException.class,
                () -> userService.save(mapper.map(users.get(1), UserDTO.class)));
    }

    @ParameterizedTest
    @MethodSource("provideUser")
    void deleteUserTest(User user) {
        userService.save(mapper.map(user, UserDTO.class));
        userService.delete(userRepository.readByEmail(user.getEmail()).get().getId());
        assertThrows(NoSuchElementException.class,
                () -> userService.findById(userRepository.readByEmail(user.getEmail()).get().getId()));
    }

    @ParameterizedTest
    @MethodSource("provideUser")
    void updateUserTest(User user) {
        userService.save(mapper.map(user, UserDTO.class));

        user.setBalance(100.0);
        user.setAvatarName("new_avatar_name");
        userService.update(mapper.map(user, UserDTO.class));

        System.out.println(userRepository.readByEmail(user.getEmail()).get().getId());
        User storedUser = userRepository.findById(userRepository.readByEmail(user.getEmail()).get().getId()).get();

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
