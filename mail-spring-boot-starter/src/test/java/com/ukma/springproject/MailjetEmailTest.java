package com.ukma.springproject;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.transactional.SendContact;
import com.mailjet.client.transactional.SendEmailsRequest;
import com.mailjet.client.transactional.TransactionalEmail;
import com.mailjet.client.transactional.response.SendEmailsResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
public class MailjetEmailTest {

    @Value("${mailjet.mail.public}")
    private String publicKey;
    @Value("${mailjet.mail.private}")
    private String privateKey;

    @Test
    public void sendEmail() {
        ClientOptions options = ClientOptions.builder()
                .apiKey(publicKey)
                .apiSecretKey(privateKey)
                .build();
        MailjetClient client = new MailjetClient(options);

        TransactionalEmail message = TransactionalEmail
                .builder()
                .to(new SendContact("dan05072003@gmail.com"))
                .from(new SendContact("dan05072003@gmail.com"))
                .subject("Test email")
                .htmlPart("<h1> Hello. Just testing</h1>")
                .build();
        SendEmailsRequest request = SendEmailsRequest
                .builder()
                .message(message)
                .build();


        assertDoesNotThrow(() -> {
            SendEmailsResponse response = request.sendWith(client);
            System.out.println(response);
        });


    }
}
