package com.ukma.springproject.service.impl;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.ukma.springproject.service.EmailService;
import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailServiceImpl implements EmailService {

    @Value("${mail.sendgrid.key}")
    private String api_key;

    @Override
    public boolean sendEmail(String to, String subject, String message) {
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
            return response.getStatusCode() < 400;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}