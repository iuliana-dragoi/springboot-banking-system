package com.example.bankingsystem.bank.country.RO;

import com.example.bankingsystem.bank.TaxReporter;

import java.math.BigDecimal;

public class RoTaxReporter implements TaxReporter {

    @Override
    public void reportTransaction(String transactionId, BigDecimal amount) {
        System.out.println("Reporting transaction to ANAF: " + transactionId);
    }
}
