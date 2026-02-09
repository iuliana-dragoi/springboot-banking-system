package com.example.bankingsystem.notification.service;

import com.example.bankingsystem.notification.model.Notification;

public interface NotificationService {
    void sendEmailNotification(String recipient, Notification notification);
    void sendSMSNotification(String recipient, Notification notification);
}
