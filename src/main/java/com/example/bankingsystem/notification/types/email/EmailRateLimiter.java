package com.example.bankingsystem.notification.types.email;

import com.example.bankingsystem.notification.factory.RateLimiter;

public class EmailRateLimiter implements RateLimiter {

    private int sent = 0;
    private final int limit = 100;

    @Override
    public boolean allowSend() {
        if (sent < limit) {
            sent++;
            return true;
        }
        return false;
    }
}
