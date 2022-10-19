package com.ukma.springproject.autoconfiguration;

import com.ukma.springproject.services.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {

    @Autowired
    private EmailService email;

    @Test
    void testEmailService() {
        email.sendEmail(null,null, null);
    }
}
