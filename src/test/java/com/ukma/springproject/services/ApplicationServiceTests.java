package com.ukma.springproject.services;

import com.ukma.springproject.domain.Application;
import com.ukma.springproject.domain.Genre;
import com.ukma.springproject.domain.Role;
import com.ukma.springproject.domain.User;
import com.ukma.springproject.repositories.ApplicationRepository;
import com.ukma.springproject.repositories.GenreRepository;
import com.ukma.springproject.repositories.UserRepository;
import com.ukma.springproject.service.ApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties = "spring.datasource.url=jdbc:h2:file:./src/main/resources/data/test-store")
public class ApplicationServiceTests {

    @Autowired
    private ApplicationService appService;

    @Autowired
    private ApplicationRepository appRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private UserRepository userRepository;

    private static User user;
    private static User admin;
    private static User dev;
    private static Genre gen;

    @BeforeEach
    private void init(){
        userRepository.deleteAll();
        appRepository.deleteAll();
        genreRepository.deleteAll();

        user = new User();
        user.setUsername("user");
        user.setEmail("user@email");
        user.setRole(Role.ROLE_USER);
        user.setPassword("12345");

        dev = new User();
        dev.setUsername("developer");
        dev.setEmail("dev@email");
        dev.setRole(Role.ROLE_DEV);
        dev.setPassword("12345");

        admin = new User();
        admin.setUsername("admin");
        admin.setEmail("admin@email");
        admin.setRole(Role.ROLE_ADMIN);
        admin.setPassword("12345");

        gen = new Genre("strategy");

        userRepository.save(user);
        userRepository.save(admin);
        userRepository.save(dev);
        genreRepository.save(gen);
    }

    @ParameterizedTest
    @MethodSource("provideApplication")
    void saveAndFindApplicationTest(Application application) {
        application.setGenres(List.of(gen));
        appService.create(application, dev);
        assertEquals(appRepository.readAllByDeveloperId(dev.getId()), List.of(application));

        Application storedApp = appService.findByDeveloper(dev.getId()).get(0);
        assertNotNull(storedApp.getId());
    }

    private static Stream<Application> provideApplication() {
        Application app = new Application();
        app.setName("Game");
        app.setPrice(10.0);
        app.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        app.setImage("image.png");
        return Stream.of(app);
    }




}
