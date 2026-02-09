package com.example.bankingsystem.notification.types.sms;

import com.example.bankingsystem.notification.factory.Sender;

public class SmsSender implements Sender {

    @Override
    public void send(String recipient, String message) {
        System.out.println("Sending SMS to " + recipient + ": " + message);
        // Twilio logic here
    }
}
