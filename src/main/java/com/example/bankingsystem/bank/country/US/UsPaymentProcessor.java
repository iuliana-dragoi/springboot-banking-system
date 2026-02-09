package com.example.bankingsystem.bank.country.US;

import com.example.bankingsystem.bank.PaymentProcessor;

import java.math.BigDecimal;

public class UsPaymentProcessor implements PaymentProcessor {

    @Override
    public void processPayment(String fromAccount, String toAccount, BigDecimal amount) {
        System.out.println("Processing ACH payment in US");    }
}
