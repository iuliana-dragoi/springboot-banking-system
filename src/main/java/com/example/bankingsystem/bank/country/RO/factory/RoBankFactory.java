package com.example.bankingsystem.bank.country.RO.factory;

import com.example.bankingsystem.bank.*;
import com.example.bankingsystem.bank.country.RO.RoAccountValidator;
import com.example.bankingsystem.bank.country.RO.RoBankDocument;
import com.example.bankingsystem.bank.country.RO.RoPaymentProcessor;
import com.example.bankingsystem.bank.country.RO.RoTaxReporter;
import org.springframework.stereotype.Component;

@Component("roFactory")
public class RoBankFactory implements CountryBankFactory {

    @Override
    public AccountValidator createValidator() {
        return new RoAccountValidator();
    }

    @Override
    public BankDocument createDocument() {
        return new RoBankDocument();
    }

    @Override
    public PaymentProcessor createPaymentProcessor() {
        return new RoPaymentProcessor();
    }

    @Override
    public TaxReporter createTaxReporter() {
        return new RoTaxReporter();
    }
}
