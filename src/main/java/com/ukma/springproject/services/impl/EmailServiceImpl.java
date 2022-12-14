package com.ukma.springproject.services.impl;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.ukma.springproject.services.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@ConditionalOnProperty(prefix = "mail", name = "useDefaultMail", havingValue = "false" )
public class EmailServiceImpl implements EmailService {

    @Value("${mail.sendgrid.key}")
    private String api_key;

    @Override
    public void sendEmail(String to, String subject, String message) {
        Email fromEmail = new Email("danylo.nechyporchuk@ukma.edu.ua");
        Email toEmail = new Email(to);
        Content content = new Content("text/plain", message);
        Mail email = new Mail(fromEmail, subject, toEmail, content);

        SendGrid sendgrid = new SendGrid(api_key);
        Request request = new Request();
        try{
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(email.build());

            Response response = sendgrid.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}