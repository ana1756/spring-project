package com.ukma.springproject.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ukma.springproject.domain.Application;
import com.ukma.springproject.domain.Category;
import com.ukma.springproject.domain.Role;
import com.ukma.springproject.domain.User;
import com.ukma.springproject.domain.dto.ApplicationDTO;
import com.ukma.springproject.repositories.ApplicationRepository;
import com.ukma.springproject.repositories.CategoryRepository;
import com.ukma.springproject.repositories.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.sql.Timestamp;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = "spring.datasource.url=jdbc:h2:file:./test-file-h2.db")
@TestPropertySource(properties = "spring.jpa.hibernate.ddl-auto=create-drop")
@TestPropertySource(properties = "spring.main.allow-bean-definition-overriding=true")
@AutoConfigureMockMvc
@Transactional
class RestApplicationControllerTest {

    @BeforeEach
    void initDatabase(@Autowired UserRepository userRepository, @Autowired ApplicationRepository applicationRepository,
                      @Autowired CategoryRepository categoryRepository) {
        applicationRepository.deleteAll();
        userRepository.deleteAll();
        categoryRepository.deleteAll();
        User user1 = new User();
        user1.setEmail("adminTestLocal@admin.com");
        user1.setRole(Role.ROLE_ADMIN);
        user1.setUsername("admin1");
        user1.setPassword(encoder.encode("admin"));
        user1 = userRepository.save(user1);
        User user2 = new User();
        user2.setEmail("dev@admin.com");
        user2.setRole(Role.ROLE_DEV);
        user2.setUsername("dev");
        user2.setPassword(encoder.encode("admin"));
        user2 = userRepository.save(user2);
        Application application = new Application();
        application.setName("Name");
        application.setImage("image");
        application.setDescription("desc");
        application.setPrice(1.21);
        application.setDeveloper(user2);
        application.setDateCreated(new Timestamp(System.currentTimeMillis()));
        Category category = new Category();
        category.setName("ADMIN");
        applicationRepository.save(application);
    }

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    ModelMapper mapper;

    private final ObjectMapper jsonMapper = new ObjectMapper();

    @Test
    @WithMockUser(username = "adminTestLocal@admin.com", password = "admin", authorities = {"ROLE_ADMIN"})
    void testInitialDataAndTestListingAllApplications() throws Exception {
        var res = mockMvc.perform(get("/rest/application/all")).andExpect(status().isOk()).andReturn();
        Assertions.assertThat(res.getResponse().getContentAsString())
                .contains("dev@admin.com").doesNotContain("adminTestLocal@admin.com");
    }

    @Test
    @WithMockUser(username = "dev@admin.com", password = "admin", authorities = {"ROLE_DEV"})
    void testCreateApplication() throws Exception {
        Application application = new Application();
        application.setName("NewName");
        application.setPrice(2.433);
        application.setDeveloper(userRepository.readByEmail("dev@admin.com").orElseThrow(Exception::new));
        application.setDateCreated(new Timestamp(System.currentTimeMillis()));
        mockMvc.perform(post("/rest/application/create").contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsString(mapper.map(application, ApplicationDTO.class))))
                .andExpect(status().isOk());
    }

//    @Test
//    void testDeleteApplication() {
//        var user = userRepository.findByEmail("adminTestLocal@admin.com").orElseThrow();
//        Application application = applicationRepository.findApplicationByDeveloperId(user.getId()).get(0);
//        Assertions.assertThat(restTemplate.getForObject("http://localhost:" + port + "/application/all", String.class))
//                .contains(application.getId().toString());
//        Assertions.assertThat(restTemplate.getForObject("http://localhost:" + port + "/application/" + application.getId(), String.class))
//                .contains(user.getEmail()).contains(user.getId().toString());
//        restTemplate
//                .delete("http://localhost:" + port + "/application/" + application.getId());
//        Assertions.assertThat(restTemplate.getForObject("http://localhost:" + port + "/application/all", String.class))
//                .doesNotContain(application.getId().toString());
//    }
//
//    @Test
//    void testBadRequestPrice() throws JsonProcessingException {
//        var user = userRepository.findByEmail("adminTestLocal@admin.com").orElseThrow();
//        Application application = new Application();
//        application.setName("VeryNewName");
//        application.setPrice(-2.433);
//        application.setDeveloper(user);
//        application.setDateCreated(new Timestamp(System.currentTimeMillis()));
//        application.setCategory(categoryRepository.findCategoryByName("ADMIN").orElseThrow());
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<String> request =
//                new HttpEntity<>(jsonMapper.writeValueAsString(application), headers);
//        ResponseEntity<String> application1 = restTemplate
//                .postForEntity("http://localhost:" + port + "/application/create", request, String.class);
//
//        Assertions.assertThat(application1).isNotNull();
//        Assertions.assertThat(application1.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
//    }
}
