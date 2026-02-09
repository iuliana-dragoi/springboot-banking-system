package com.example.bankingsystem.payment;

import java.math.BigDecimal;

public class FeePaymentDecorator implements PaymentGateway {

    private final PaymentGateway delegate;
    private final BigDecimal fee;

    public FeePaymentDecorator(PaymentGateway delegate, BigDecimal fee) {
        this.delegate = delegate;
        this.fee = fee;
    }

    @Override
    public boolean pay(String accountFrom, String accountTo, BigDecimal amount) {
        BigDecimal totalAmount = amount.add(fee);
        System.out.println("[DECORATOR] Fee applied: " + fee + ", total: " + totalAmount);
        return delegate.pay(accountFrom, accountTo, totalAmount);
    }
}
