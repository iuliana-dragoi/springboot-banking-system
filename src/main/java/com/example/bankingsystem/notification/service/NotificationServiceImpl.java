package com.example.bankingsystem.notification.service;

import com.example.bankingsystem.notification.factory.Formatter;
import com.example.bankingsystem.notification.factory.NotificationSystemFactory;
import com.example.bankingsystem.notification.factory.RateLimiter;
import com.example.bankingsystem.notification.factory.Sender;
import com.example.bankingsystem.notification.model.Notification;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationSystemFactory emailFactory;
    private final NotificationSystemFactory smsFactory;

    public NotificationServiceImpl(
            @Qualifier("emailFactory") NotificationSystemFactory emailFactory,
            @Qualifier("smsFactory") NotificationSystemFactory smsFactory) {
        this.emailFactory = emailFactory;
        this.smsFactory = smsFactory;
    }

    @Override
    public void sendEmailNotification(String recipient, Notification notification) {
        Sender sender = emailFactory.createSender();
        Formatter formatter = emailFactory.createFormatter();
        RateLimiter limiter = emailFactory.createRateLimiter();

        String message = formatter.format(notification);
        if (limiter.allowSend()) {
            sender.send(recipient, message);
        }
    }

    @Override
    public void sendSMSNotification(String recipient, Notification notification) {
        Sender sender = smsFactory.createSender();
        Formatter formatter = smsFactory.createFormatter();
        RateLimiter limiter = smsFactory.createRateLimiter();

        String message = formatter.format(notification);
        if (limiter.allowSend()) {
            sender.send(recipient, message);
        }
    }
}
