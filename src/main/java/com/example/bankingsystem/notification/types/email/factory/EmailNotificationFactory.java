package com.example.bankingsystem.notification.types.email.factory;

import com.example.bankingsystem.notification.types.email.EmailFormatter;
import com.example.bankingsystem.notification.types.email.EmailRateLimiter;
import com.example.bankingsystem.notification.types.email.EmailSender;
import com.example.bankingsystem.notification.types.email.EmailTemplateEngine;
import com.example.bankingsystem.notification.factory.*;
import org.springframework.stereotype.Component;

@Component("emailFactory")
public class EmailNotificationFactory implements NotificationSystemFactory {

    @Override
    public Formatter createFormatter() {
        return new EmailFormatter();
    }

    @Override
    public Sender createSender() {
        return new EmailSender();
    }

    @Override
    public RateLimiter createRateLimiter() {
        return new EmailRateLimiter();
    }

    @Override
    public TemplateEngine createTemplateEngine() {
        return new EmailTemplateEngine();
    }
}
