package com.example.bankingsystem.bank;

import java.math.BigDecimal;

public interface TaxReporter {
    void reportTransaction(String transactionId, BigDecimal amount);
}
