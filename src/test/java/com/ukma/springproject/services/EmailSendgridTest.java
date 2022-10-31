package com.ukma.springproject.services;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Disabled
public class EmailSendgridTest {

    @Value("${mail.sendgrid.key}")
    private String api_key;

    @Test
    void sendTestEmail() {
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
