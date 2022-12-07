package com.ukma.springproject.rest;

import com.ukma.springproject.domain.Application;
import com.ukma.springproject.domain.Category;
import com.ukma.springproject.domain.Role;
import com.ukma.springproject.domain.User;
import com.ukma.springproject.repositories.ApplicationRepository;
import com.ukma.springproject.repositories.CategoryRepository;
import com.ukma.springproject.repositories.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.sql.Timestamp;

@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = "spring.datasource.url=jdbc:h2:file:./test-file-h21.db")
@TestPropertySource(properties = "spring.jpa.hibernate.ddl-auto=create-drop")
@TestPropertySource(properties = "spring.main.allow-bean-definition-overriding=true")
class ApplicationTests {

    @TestConfiguration
    static class LoadDatabase {

        @Bean
        CommandLineRunner initDatabase(UserRepository userRepository, ApplicationRepository applicationRepository, CategoryRepository categoryRepository) {

            return args -> {
                User user1 = new User();
                user1.setEmail("adminTestLocal@admin.com");
                user1.setRole(Role.ROLE_ADMIN);
                user1.setUsername("admin");
                user1.setPassword("admin");
                user1 = userRepository.save(user1);
                Application application = new Application();
                application.setName("Name");
                application.setImage("image");
                application.setDescription("desc");
                application.setPrice(1.21);
                application.setDeveloper(user1);
                application.setDateCreated(new Timestamp(System.currentTimeMillis()));
                Category category = new Category();
                category.setName("ADMIN");
                applicationRepository.save(application);
            };
        }
    }

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Autowired
    private WebTestClient webTestClient;


    @Test
    void testInitialDataAndTestListingAllApplicationsWebTestClient() {
        webTestClient.get()
                .uri("/applications")
                .exchange()
                .expectStatus()
                .is3xxRedirection();
    }

    @Test
    void shouldReturnNotFoundForUnknownApplicationId() {
        this.webTestClient
                .get()
                .uri("/applications/{id}", Integer.MAX_VALUE)
                .exchange()
                .expectStatus()
                .isEqualTo(404);
    }
}