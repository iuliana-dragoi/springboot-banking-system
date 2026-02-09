package com.example.bankingsystem.notification;

import com.example.bankingsystem.notification.factory.NotificationSystemFactory;
import com.example.bankingsystem.notification.types.email.factory.EmailNotificationFactory;
import com.example.bankingsystem.notification.types.sms.factory.SmsNotificationFactory;

public class NotificationFactoryProvider {

    public enum Channel { EMAIL, SMS };

    public static NotificationSystemFactory getFactory(Channel channel) {
        return switch (channel) {
            case EMAIL -> new EmailNotificationFactory();
            case SMS -> new SmsNotificationFactory();
        };
    }
}
