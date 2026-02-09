package com.example.bankingsystem.notification.types.email;

import com.example.bankingsystem.notification.factory.Formatter;
import com.example.bankingsystem.notification.model.Notification;

public class EmailFormatter implements Formatter {

    @Override
    public String format(Notification notification) {
        return "[Email] " + notification.message();
    }
}
