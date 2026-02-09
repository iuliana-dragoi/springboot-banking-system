package com.example.bankingsystem.payment.adapter;

import com.example.bankingsystem.payment.PaymentGateway;
import com.example.bankingsystem.payment.adaptee.PayPalService;

import java.math.BigDecimal;

public class PayPalAdapter implements PaymentGateway {

    private final PayPalService payPalService;

    public PayPalAdapter(PayPalService payPalService) {
        this.payPalService = payPalService;
    }

    @Override
    public boolean pay(String accountFrom, String accountTo, BigDecimal amount) {
        return payPalService.sendPayment(accountFrom, accountTo, amount.doubleValue());
    }
}
