package com.example.bankingsystem.notification.factory;

public interface RateLimiter {
    boolean allowSend();
}
