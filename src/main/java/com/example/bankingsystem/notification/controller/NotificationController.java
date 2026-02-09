package com.example.bankingsystem.notification.controller;

import com.example.bankingsystem.notification.model.Notification;
import com.example.bankingsystem.notification.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    public final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/email")
    public ResponseEntity<String> sendEmail(@RequestParam String recipient) {
        Notification notification = new Notification("Notification test via Email");
        notificationService.sendEmailNotification(recipient, notification);
        return ResponseEntity.ok("Email sent");
    }

    @PostMapping("/sms")
    public ResponseEntity<String> sendSMS(@RequestParam String recipient) {
        Notification notification = new Notification("Notification test via SMS");
        notificationService.sendSMSNotification(recipient, notification);
        return ResponseEntity.ok("SMS sent");
    }
}
