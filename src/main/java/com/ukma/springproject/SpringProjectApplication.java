package com.ukma.springproject;

import com.ukma.springproject.domain.Application;
import com.ukma.springproject.domain.Category;
import com.ukma.springproject.domain.Role;
import com.ukma.springproject.domain.User;
import com.ukma.springproject.repositories.ApplicationRepository;
import com.ukma.springproject.repositories.CategoryRepository;
import com.ukma.springproject.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Timestamp;

@SpringBootApplication
public class SpringProjectApplication {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, ApplicationRepository applicationRepository,
                                   CategoryRepository categoryRepository, PasswordEncoder encoder) {

        return args -> {
            User user1 = new User();
            user1.setEmail("admin@admin.com");
            user1.setRole(Role.ROLE_ADMIN);
            user1.setUsername("admin");
            user1.setPassword(encoder.encode("admin"));
            Application application = new Application();
            application.setName("Name");
            application.setImage("image");
            application.setDescription("desc");
            application.setPrice(1.21);
            application.setDeveloper(user1);
            application.setPublished(false);
            application.setDateCreated(new Timestamp(System.currentTimeMillis()));
            Category category = new Category();
            category.setName("ADMIN");
            applicationRepository.deleteAllByDeveloperEmail(user1.getEmail());
            userRepository.deleteByEmail(user1.getEmail());
            userRepository.save(user1);
            applicationRepository.save(application);
            categoryRepository.delete(category);
            categoryRepository.save(category);
        };
    }


    public static void main(String[] args) {
        SpringApplication.run(SpringProjectApplication.class, args);
    }

}
