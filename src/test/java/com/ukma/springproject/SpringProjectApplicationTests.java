package com.ukma.springproject;

import com.ukma.springproject.domain.User;
import com.ukma.springproject.repositories.UserRepository;
import com.ukma.springproject.services.EmailService;
import com.ukma.springproject.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = "spring.datasource.url=jdbc:sqlite:test-file.db")
class SpringProjectApplicationTests {

    @TestConfiguration
    static class testConf {
        @Bean
        public EmailService emailService() {
            return (to, subject, message) -> outputWrapper.write(to + subject + message);
        }

        private static final TestOutputWrapper outputWrapper = new TestOutputWrapper();

        public static TestOutputWrapper getOutputWrapper() {
            return outputWrapper;
        }
    }

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testUserServiceInsertion() {
        userRepository.deleteAll();
        User user = new User();
        user.setEmail("newemail@me.com");
        user.setRole(User.CLIENT);
        user.setFirstName("newemail@me.com");
        user.setLastName("newemail@me.com");
        user.setPassword("newemail@me.com");
        userService.save(user);
        Assertions.assertEquals(user, userService.findByEmail("newemail@me.com"));
    }

    @Test
    void testUserServiceInsertionSameEmailCuriousFail() {
        userRepository.deleteAll();
        User user = new User();
        user.setEmail("newemail@me.com");
        user.setRole(User.CLIENT);
        user.setFirstName("newemail@me.com");
        user.setLastName("newemail@me.com");
        user.setPassword("newemail@me.com");
        User user1 = new User();
        user1.setEmail("newemail@me.com");
        user1.setRole(User.ADMIN);
        user1.setFirstName("newemail@me.com");
        user1.setLastName("newemail@me.com");
        user1.setPassword("newemail@me.com");
        userService.save(user);
       // userService.save(user1);
        // fixed
        Assertions.assertThrows(RuntimeException.class, () -> userService.save(user1),
                "RuntimeException must be thrown when the user's email isn't unique");

        //Throws exception at find,
        //but from what I understand it should throw something earlier, at save, since the email is not unique
        Assertions.assertEquals(user, userService.findByEmail("newemail@me.com"));
    }

    @Test
    void testGetAllUsersByRole() {
        userRepository.deleteAll();
        User user1 = new User();
        user1.setEmail("1@me.com");
        user1.setRole(User.ADMIN);
        user1.setFirstName("newemail@me.com");
        user1.setLastName("newemail@me.com");
        user1.setPassword("newemail@me.com");
        userService.save(user1);
        User user2 = new User();
        user2.setEmail("2@me.com");
        user2.setRole(User.ADMIN);
        user2.setFirstName("newemail@me.com");
        user2.setLastName("newemail@me.com");
        user2.setPassword("newemail@me.com");
        userService.save(user2);
        User user3 = new User();
        user3.setEmail("3@me.com");
        user3.setRole(User.ADMIN);
        user3.setFirstName("newemail@me.com");
        user3.setLastName("newemail@me.com");
        user3.setPassword("newemail@me.com");
        userService.save(user3);
        String expectedOutput = user1.getEmail() + user2.getEmail() + user3.getEmail();
        userService.notifyAdminsByEmail("");
        Assertions.assertEquals(expectedOutput, testConf.getOutputWrapper().getOutput());
    }

    @Test
    void insertUsersWithTheSameEmailsTest() {
        userRepository.deleteAll();
        User user = new User("Name", "Surname",
                "email@mail.com", "12345", User.CLIENT);
        User user1 = new User("Name1", "Surname1",
                "email@mail.com", "54321", User.DEVELOPER);

        userService.save(user);
        Assertions.assertThrows(RuntimeException.class, () -> userService.save(user1));
    }

}
