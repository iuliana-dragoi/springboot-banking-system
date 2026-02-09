package com.example.bankingsystem.payment.adapter;

import com.example.bankingsystem.payment.PaymentGateway;
import com.example.bankingsystem.payment.adaptee.LocalAPI;

import java.math.BigDecimal;

public class LocalProcessorAdapter implements PaymentGateway {

    private final LocalAPI localAPI;

    public LocalProcessorAdapter(LocalAPI localAPI) {
        this.localAPI = localAPI;
    }

    @Override
    public boolean pay(String accountFrom, String accountTo, BigDecimal amount) {
        return localAPI.transfer(accountFrom, accountTo, amount.doubleValue());
    }
}
