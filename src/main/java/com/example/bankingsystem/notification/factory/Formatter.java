package com.example.bankingsystem.notification.factory;

import com.example.bankingsystem.notification.model.Notification;

public interface Formatter {
    String format(Notification notification);
}
