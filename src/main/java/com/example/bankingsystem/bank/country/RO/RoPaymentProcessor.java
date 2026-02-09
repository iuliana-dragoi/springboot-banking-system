package com.example.bankingsystem.bank.country.RO;

import com.example.bankingsystem.bank.PaymentProcessor;

import java.math.BigDecimal;

public class RoPaymentProcessor implements PaymentProcessor {

    @Override
    public void processPayment(String fromAccount, String toAccount, BigDecimal amount) {
        System.out.println("Processing SEPA payment in Romania");
    }
}
