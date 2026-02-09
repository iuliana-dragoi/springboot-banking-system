package com.example.bankingsystem.bank;

import java.math.BigDecimal;

public interface PaymentProcessor {
    void processPayment(String fromAccount, String toAccount, BigDecimal amount);
}
