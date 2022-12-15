package com.ukma.springproject.service.impl;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.ukma.springproject.domain.Application;
import com.ukma.springproject.domain.Key;
import com.ukma.springproject.domain.User;
import com.ukma.springproject.service.EmailService;
import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@ConditionalOnProperty(prefix = "mail", name = "useRealEmail", havingValue = "true" )
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

    @Override
    public void sendAfterApplicationPublishing(Application app) {
        String message = "Hello, " + app.getDeveloper().getUsername() + "!\n" +
                         "We have considered your application and are glad to inform you " +
                         "that your game \"" + app.getName() + "\" was published in our store. Isn't that great?\n" +
                         "Now you can see your game at our \"Store\" page.";

        sendEmail(app.getDeveloper().getEmail(), app.getName(), message);
    }

    @Override
    public void sendAfterApplicationCreation(Application app, List<String> admins) {
        String message = "There is a new application to publish a game.\n" +
                         "Name: " + app.getName() + '\n' +
                         "Developer: " + app.getDeveloper().getUsername() + '\n' +
                         "Developer email: " + app.getDeveloper().getEmail();

        for(String admin: admins){
            sendEmail(admin, app.getName() + " application", message);
        }
    }

    @Override
    public void sendAfterKeyBuying(Key key) {
        Application app = key.getProduct().getApplication();

        String message = "Hello, " + key.getUser().getUsername() + "!\n" +
                         "You've just bought \"" + app.getName() + "\" in our store.\n" +
                         "This is confirmation email. You can also see your key on our site.\n" +
                         "\nKey:" + key.getValue() + "\n\n" +
                         "Have a lovely day!";

        sendEmail(app.getDeveloper().getEmail(), app.getName(), message);
    }
}