package com.example.bankingsystem.payment;

import java.math.BigDecimal;

public class PaymentGatewayProxy implements PaymentGateway {

    private final PaymentGateway realGateway;

    public PaymentGatewayProxy(PaymentGateway realGateway) {
        this.realGateway = realGateway;
    }

    @Override
    public boolean pay(String accountFrom, String accountTo, BigDecimal amount) {
        System.out.println("[LOG] Attempting payment from " + accountFrom + " to " + accountTo + " of " + amount);
        boolean result = realGateway.pay(accountFrom, accountTo, amount);
        System.out.println("[LOG] Payment status: " + (result ? "SUCCESS" : "FAILED"));
        return result;
    }
}
