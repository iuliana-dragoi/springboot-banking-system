package com.example.bankingsystem.payment.adapter;

import com.example.bankingsystem.payment.PaymentGateway;
import com.example.bankingsystem.payment.adaptee.VisaDirect;

import java.math.BigDecimal;

public class VisaAdapter implements PaymentGateway {

    private final VisaDirect visaDirect;

    public VisaAdapter(VisaDirect visaDirect) {
        this.visaDirect = visaDirect;
    }

    @Override
    public boolean pay(String accountFrom, String accountTo, BigDecimal amount) {
        return visaDirect.executeTransaction(accountFrom, accountTo, amount.doubleValue());
    }
}
