package com.ukma.springproject;

import com.ukma.springproject.domain.*;
import com.ukma.springproject.repositories.ApplicationRepository;
import com.ukma.springproject.repositories.CategoryRepository;
import com.ukma.springproject.repositories.GenreRepository;
import com.ukma.springproject.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Timestamp;

@SpringBootApplication
public class SpringProjectApplication {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository,
                                   ApplicationRepository applicationRepository,
                                   CategoryRepository categoryRepository,
                                   GenreRepository genreRepository,
                                   PasswordEncoder encoder) {

        return args -> {
            User admin = new User();
            admin.setEmail("admin@admin.com");
            admin.setRole(Role.ROLE_ADMIN);
            admin.setUsername("admin");
            admin.setPassword(encoder.encode("admin"));

            User dev = new User();
            dev.setEmail("dev@dev.com");
            dev.setRole(Role.ROLE_DEV);
            dev.setUsername("developer");
            dev.setPassword(encoder.encode("dev"));

            User user = new User();
            user.setEmail("user@user.com");
            user.setRole(Role.ROLE_USER);
            user.setUsername("user");
            user.setPassword(encoder.encode("user"));

//            Application application = new Application();
//            application.setName("Name");
//            application.setImage("image");
//            application.setDescription("desc");
//            application.setPrice(1.21);
//            application.setDeveloper(dev);
//            application.setDateCreated(new Timestamp(System.currentTimeMillis()));

            Category free = new Category();
            free.setName("RELEASE");

            Category demo = new Category();
            demo.setName("DEMO");

            Category paid = new Category();
            paid.setName("BETA ACCESS");


            Genre action = new Genre();
            action.setName("action");

            Genre sports = new Genre();
            sports.setName("sports");

            Genre strategy = new Genre();
            strategy.setName("strategy");

            Genre simulation = new Genre();
            simulation.setName("simulation");

            Genre logic = new Genre();
            logic.setName("logic");

            Genre adventure = new Genre();
            adventure.setName("adventure");

            genreRepository.save(action);
            genreRepository.save(sports);
            genreRepository.save(strategy);
            genreRepository.save(simulation);
            genreRepository.save(logic);
            genreRepository.save(adventure);

//            applicationRepository.deleteAllByDeveloperEmail(user1.getEmail());


            userRepository.deleteByEmail(user.getEmail());
            userRepository.deleteByEmail(admin.getEmail());
            userRepository.deleteByEmail(dev.getEmail());
            userRepository.save(admin);
            userRepository.save(dev);
            userRepository.save(user);

            categoryRepository.delete(free);
            categoryRepository.delete(demo);
            categoryRepository.delete(paid);
            categoryRepository.save(free);
            categoryRepository.save(demo);
            categoryRepository.save(paid);
        };
    }


    public static void main(String[] args) {
        SpringApplication.run(SpringProjectApplication.class, args);
    }

}
