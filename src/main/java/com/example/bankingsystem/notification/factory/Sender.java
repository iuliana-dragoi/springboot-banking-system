package com.example.bankingsystem.notification.factory;

public interface Sender {
    void send(String recipient, String message);
}
