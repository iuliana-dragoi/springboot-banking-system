package com.example.bankingsystem.notification.types.sms;

import com.example.bankingsystem.notification.factory.RateLimiter;

public class SmsRateLimiter implements RateLimiter {

    private int sent = 0;
    private final int limit = 10;

    @Override
    public boolean allowSend() {
        if (sent < limit) {
            sent++;
            return true;
        }
        return false;
    }
}
