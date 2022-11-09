package com.ukma.springproject;

import com.ukma.springproject.domain.Application;
import com.ukma.springproject.domain.Category;
import com.ukma.springproject.domain.User;
import com.ukma.springproject.repositories.ApplicationRepository;
import com.ukma.springproject.repositories.CategoryRepository;
import com.ukma.springproject.repositories.UserRepository;
import com.ukma.springproject.services.EuroConversionRateService;
import com.ukma.springproject.services.NameNationalityPredictorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Timestamp;

@Configuration
class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository,
                                   ApplicationRepository applicationRepository,
                                   CategoryRepository categoryRepository,
                                   NameNationalityPredictorService currencyExchangeService,
                                   EuroConversionRateService euroConversionRateService) {

        return args -> {
            User user1 = new User();
            user1.setEmail("admin@admin.com");
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
            System.out.println(currencyExchangeService.getNationality("Ukraine"));
            System.out.println(euroConversionRateService.getEuroConversionRate());
        };
    }
}
