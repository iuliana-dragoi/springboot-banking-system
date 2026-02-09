package com.example.bankingsystem.notification.types.sms.factory;

import com.example.bankingsystem.notification.factory.*;
import com.example.bankingsystem.notification.types.sms.SmsFormatter;
import com.example.bankingsystem.notification.types.sms.SmsRateLimiter;
import com.example.bankingsystem.notification.types.sms.SmsSender;
import com.example.bankingsystem.notification.types.sms.SmsTemplateEngine;
import org.springframework.stereotype.Component;

@Component("smsFactory")
public class SmsNotificationFactory implements NotificationSystemFactory {

    @Override
    public Formatter createFormatter() {
        return new SmsFormatter();
    }

    @Override
    public Sender createSender() {
        return new SmsSender();
    }

    @Override
    public RateLimiter createRateLimiter() {
        return new SmsRateLimiter();
    }

    @Override
    public TemplateEngine createTemplateEngine() {
        return new SmsTemplateEngine();
    }
}
