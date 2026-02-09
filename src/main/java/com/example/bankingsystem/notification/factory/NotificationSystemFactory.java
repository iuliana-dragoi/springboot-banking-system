package com.example.bankingsystem.notification.factory;

public interface NotificationSystemFactory {
    Formatter createFormatter();
    Sender createSender();
    RateLimiter createRateLimiter();
    TemplateEngine createTemplateEngine();
}
