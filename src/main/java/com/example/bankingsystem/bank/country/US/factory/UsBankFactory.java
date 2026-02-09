package com.example.bankingsystem.bank.country.US.factory;

import com.example.bankingsystem.bank.*;
import com.example.bankingsystem.bank.country.US.UsAccountValidator;
import com.example.bankingsystem.bank.country.US.UsBankDocument;
import com.example.bankingsystem.bank.country.US.UsPaymentProcessor;
import com.example.bankingsystem.bank.country.US.UsTaxReporter;
import org.springframework.stereotype.Component;

@Component("usFactory")
public class UsBankFactory implements CountryBankFactory {

    @Override
    public AccountValidator createValidator() {
        return new UsAccountValidator();
    }

    @Override
    public BankDocument createDocument() {
        return new UsBankDocument();
    }

    @Override
    public PaymentProcessor createPaymentProcessor() {
        return new UsPaymentProcessor();
    }

    @Override
    public TaxReporter createTaxReporter() {
        return new UsTaxReporter();
    }
}
