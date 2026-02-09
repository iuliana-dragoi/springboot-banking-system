package com.example.bankingsystem.transaction.config;

import java.util.concurrent.atomic.AtomicLong;

public enum TransactionIdGenerator {

    INSTANCE;

    private final AtomicLong counter;
    private final String prefix;

    TransactionIdGenerator() {
        this.counter = new AtomicLong(System.currentTimeMillis());
        this.prefix = "TXN";
    }

    public String generatedId() {
        return prefix + "-" + counter.incrementAndGet();
    }

    public void reset(long startValue) {
        counter.set(startValue);
    }
}
