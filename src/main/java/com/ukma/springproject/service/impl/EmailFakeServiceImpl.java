package com.ukma.springproject.service.impl;

import com.ukma.springproject.domain.Application;
import com.ukma.springproject.domain.Key;
import com.ukma.springproject.service.EmailService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
@ConditionalOnProperty(prefix = "mail", name = "useRealEmail", havingValue = "false" )
public class EmailFakeServiceImpl implements EmailService {

    public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                            "[a-zA-Z0-9_+&*-]+)*@" +
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                            "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    @Override
    public boolean sendEmail(String to, String subject, String message) {
        System.out.println("To: " + to);
        System.out.println("Subject: " + subject);
        System.out.println("Message: " + message);
        return isValid(to);
    }

    @Override
    public void sendAfterApplicationPublishing(Application app) {
        sendEmail(app.getDeveloper().getEmail(), app.getName(), "Application published");
    }

    @Override
    public void sendAfterApplicationCreation(Application app, List<String> admins) {
        String admin = admins.isEmpty() ? null : admins.get(0);
        sendEmail(admin, app.getName(), "Application published");
    }

    @Override
    public void sendAfterKeyBuying(Key key) {
        sendEmail(key.getUser().getEmail(), key.getProduct().getApplication().getName(), "Key " + key.getValue());
    }
}
