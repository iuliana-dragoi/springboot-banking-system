package com.example.bankingsystem.notification.types.email;

import com.example.bankingsystem.notification.factory.Sender;

public class EmailSender implements Sender {

    @Override
    public void send(String recipient, String message) {
        System.out.println("Sending Email to " + recipient + ": " + message);
        // SMTP logic here
    }
}
