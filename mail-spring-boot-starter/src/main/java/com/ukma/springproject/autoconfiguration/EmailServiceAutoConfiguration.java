package com.ukma.springproject.autoconfiguration;

import com.ukma.springproject.services.EmailService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ConditionalOnClass(EmailService.class)
@ConditionalOnMissingBean(EmailService.class)
//@ConditionalOnProperty()
public class EmailServiceAutoConfiguration {

    @Bean
//    @ConditionalOnClass(DefaultServiceImpl.class)
    public EmailService emailService() {
        return new EmailServiceDefaultImpl();
    }
}
