package com.ukma.springproject.services;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.ukma.springproject.service.EmailService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EmailServiceTests {
    @Value("${mail.sendgrid.key}")
    private String api_key;

    @Autowired
    private EmailService emailService;

    @Test
    void sendEmailWithInvalidAddress() {
        String invalidEmail = "invalid.com";
        String correctEmail = "danylo.nechyporchuk@ukma.edu.ua";

        assertFalse(emailService.sendEmail(invalidEmail, correctEmail, "message"));
    }

    @Test
    void sendCorrectEmail() {
        String correctEmail = "danylo.nechyporchuk@ukma.edu.ua";

        assertTrue(emailService.sendEmail(correctEmail,correctEmail, "Sending correct Message"));
    }

    @Test
    @Disabled
    void testSendGridSystem() {
        Email from = new Email("danylo.nechyporchuk@ukma.edu.ua");
        Email to = new Email("danylo.nechyporchuk@ukma.edu.ua");
        String subject = "Test email via sendgrid";
        Content content = new Content("text/plain", "Hello there. Just testing email sending with Sendgrid");
        Mail email = new Mail(from, subject, to, content);
        SendGrid sendgrid = new SendGrid(api_key);
        Request request = new Request();
        Assertions.assertDoesNotThrow(() -> {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(email.build());
            Response response = sendgrid.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        });
    }
}
