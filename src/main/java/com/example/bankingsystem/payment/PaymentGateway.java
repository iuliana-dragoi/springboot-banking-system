package com.example.bankingsystem.payment;

import java.math.BigDecimal;

public interface PaymentGateway {
    boolean pay(String accountFrom, String accountTo, BigDecimal amount);
}
