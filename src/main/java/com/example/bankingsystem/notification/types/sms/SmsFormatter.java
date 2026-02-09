package com.example.bankingsystem.notification.types.sms;

import com.example.bankingsystem.notification.factory.Formatter;
import com.example.bankingsystem.notification.model.Notification;

public class SmsFormatter implements Formatter {

    @Override
    public String format(Notification notification) {
        return "[SMS] " + notification.message();
    }
}
