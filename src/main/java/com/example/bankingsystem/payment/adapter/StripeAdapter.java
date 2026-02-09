package com.example.bankingsystem.payment.adapter;

import com.example.bankingsystem.payment.PaymentGateway;
import com.example.bankingsystem.payment.adaptee.StripePayment;

import java.math.BigDecimal;

public class StripeAdapter implements PaymentGateway {

    private final StripePayment stripePayment;

    public StripeAdapter(StripePayment stripePayment) {
        this.stripePayment = stripePayment;
    }

    @Override
    public boolean pay(String accountFrom, String accountTo, BigDecimal amount) {
        return stripePayment.makePayment(accountFrom, accountTo, amount.doubleValue());
    }
}
