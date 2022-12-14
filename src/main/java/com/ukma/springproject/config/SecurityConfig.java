package com.ukma.springproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig implements WebMvcConfigurer {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .antMatchers("/profile", "/getImage", "/profile/**").authenticated()
                .antMatchers("/rest/application/all", "/rest/application/developer/**",
                        "/rest/application/{spring:\\d+}").hasAuthority("ROLE_ADMIN")
                .antMatchers("/applications", "/admin/").hasAuthority("ROLE_ADMIN")
                .antMatchers("/rest/application/create").hasAuthority("ROLE_DEV")
                .regexMatchers("/applications/create").hasAuthority("ROLE_DEV")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/profile", true)
                .failureUrl("/home")
                .and()
                .logout()
                .logoutSuccessUrl("/home");

        return http.build();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}
