package com.example.bankingsystem.bank.country.US;

import com.example.bankingsystem.bank.TaxReporter;

import java.math.BigDecimal;

public class UsTaxReporter implements TaxReporter {

    @Override
    public void reportTransaction(String transactionId, BigDecimal amount) {
        System.out.println("Reporting transaction to IRS: " + transactionId);
    }
}
