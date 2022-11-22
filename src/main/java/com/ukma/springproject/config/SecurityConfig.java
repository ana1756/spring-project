package com.ukma.springproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig implements WebMvcConfigurer {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .regexMatchers("/profile", "/keys", "/products/.*").authenticated()
                .regexMatchers(HttpMethod.GET, "/applications").hasAnyRole("ADMIN", "DEV")
                .regexMatchers(HttpMethod.POST, "/applications").hasRole("DEV")
                .regexMatchers(HttpMethod.GET, "/applications/.*").hasRole("ADMIN")
                .anyRequest().permitAll()
                .and()
                .formLogin();

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user@mail.com")
                .password("12345")
                .roles("USER")
                .build();
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin@mail.com")
                .password("12345")
                .roles("ADMIN")
                .build();
        UserDetails dev = User.withDefaultPasswordEncoder()
                .username("dev@mail.com")
                .password("12345")
                .roles("DEV")
                .build();
        return new InMemoryUserDetailsManager(user, admin, dev);
    }
}


