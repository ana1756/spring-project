package com.ukma.springproject.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ukma.springproject.domain.Application;
import com.ukma.springproject.domain.Category;
import com.ukma.springproject.domain.User;
import com.ukma.springproject.repositories.ApplicationRepository;
import com.ukma.springproject.repositories.CategoryRepository;
import com.ukma.springproject.repositories.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;

import java.sql.Timestamp;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = "spring.datasource.url=jdbc:h2:file:./test-file-h2.db")
@TestPropertySource(properties = "spring.jpa.hibernate.ddl-auto=create-drop")
@TestPropertySource(properties = "spring.main.allow-bean-definition-overriding=true")
class ApplicationControllerTest {

    @TestConfiguration
    static class LoadDatabase {

        @Bean
        CommandLineRunner initDatabase(UserRepository userRepository, ApplicationRepository applicationRepository, CategoryRepository categoryRepository) {

            return args -> {
                User user1 = new User();
                user1.setEmail("adminTestLocal@admin.com");
                user1.setRole(User.ADMIN);
                user1.setFirstName("admin");
                user1.setLastName("admin");
                user1.setPassword("admin");
                user1 = userRepository.save(user1);
                Application application = new Application();
                application.setName("Name");
                application.setPrice(1.21);
                application.setDeveloper(user1);
                application.setDateCreated(new Timestamp(System.currentTimeMillis()));
                Category category = new Category();
                category.setName("ADMIN");
                category = categoryRepository.save(category);
                application.setCategory(category);
                applicationRepository.save(application);
            };
        }
    }

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private final ObjectMapper jsonMapper = new ObjectMapper();

    @Test
    void testInitialDataAndTestListingAllApplications() {
        Assertions.assertThat(restTemplate.getForObject("http://localhost:" + port + "/application/all", String.class))
                .contains("adminTestLocal@admin.com").doesNotContain("admin@admin.com");
    }

    @Test
    void testCreateApplication() throws JsonProcessingException {
        var user = userRepository.findByEmail("adminTestLocal@admin.com").orElseThrow();
        Application application = new Application();
        application.setName("NewName");
        application.setPrice(2.433);
        application.setDeveloper(user);
        application.setDateCreated(new Timestamp(System.currentTimeMillis()));
        application.setCategory(categoryRepository.findCategoryByName("ADMIN").orElseThrow());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request =
                new HttpEntity<>(jsonMapper.writeValueAsString(application), headers);
        Application application1 = restTemplate
                .postForObject("http://localhost:" + port + "/application/create", request, Application.class);
        Assertions.assertThat(application1).isNotNull();
        Assertions.assertThat(restTemplate.getForObject("http://localhost:" + port + "/application/all", String.class))
                .contains("NewName").contains("2.433");
    }

    @Test
    void testDeleteApplication() {
        var user = userRepository.findByEmail("adminTestLocal@admin.com").orElseThrow();
        Application application = applicationRepository.findApplicationByDeveloperId(user.getId()).get(0);
        Assertions.assertThat(restTemplate.getForObject("http://localhost:" + port + "/application/all", String.class))
                .contains(application.getId().toString());
        Assertions.assertThat(restTemplate.getForObject("http://localhost:" + port + "/application/" + application.getId(), String.class))
                .contains(user.getEmail()).contains(user.getId().toString());
        restTemplate
                .delete("http://localhost:" + port + "/application/" + application.getId());
        Assertions.assertThat(restTemplate.getForObject("http://localhost:" + port + "/application/all", String.class))
                .doesNotContain(application.getId().toString());
    }
}
