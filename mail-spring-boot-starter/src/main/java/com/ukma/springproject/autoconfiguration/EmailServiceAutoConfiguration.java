package com.ukma.springproject.autoconfiguration;

import com.ukma.springproject.services.EmailService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConditionalOnMissingBean(EmailService.class)
public class EmailServiceAutoConfiguration {

    @Bean
    @ConditionalOnClass(EmailServiceDefaultImpl.class)
    public EmailService emailService() {
        return new EmailServiceDefaultImpl();
    }
}
