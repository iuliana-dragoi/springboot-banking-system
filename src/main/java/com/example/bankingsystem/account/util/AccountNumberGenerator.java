package com.example.bankingsystem.account.util;

import java.util.concurrent.atomic.AtomicLong;

public class AccountNumberGenerator {

    private static final AtomicLong COUNTER = new AtomicLong(System.currentTimeMillis() % 1_000_000);

    private AccountNumberGenerator() {}

    public static String generate() {
        long uniqueNumber = COUNTER.incrementAndGet();
        return String.format("%012d", uniqueNumber);
    }
}
