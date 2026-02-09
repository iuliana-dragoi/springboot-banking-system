package com.example.bankingsystem.bank;

public interface CountryBankFactory {
    AccountValidator createValidator();
    BankDocument createDocument();
    PaymentProcessor createPaymentProcessor();
    TaxReporter createTaxReporter();
}
