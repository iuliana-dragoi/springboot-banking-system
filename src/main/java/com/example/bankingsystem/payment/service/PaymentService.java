package com.example.bankingsystem.payment.service;

import java.math.BigDecimal;

public interface PaymentService {

    void processPayment(Long sourceAccountId, Long targetAccountId, BigDecimal amount, String gatewayType);
}
