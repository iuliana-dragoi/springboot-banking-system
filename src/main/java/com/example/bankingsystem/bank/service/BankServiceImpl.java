package com.example.bankingsystem.bank.service;

import com.example.bankingsystem.bank.AccountValidator;
import com.example.bankingsystem.bank.BankDocument;
import com.example.bankingsystem.bank.CountryBankFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class BankServiceImpl implements BankService {

    private final Map<String, CountryBankFactory> factories;

    public BankServiceImpl(Map<String, CountryBankFactory> factories) {
        this.factories = factories;
    }

    @Override
    public void onboardAccount(String country, String accountNumber) {
        CountryBankFactory factory = getFactoryByCountry(country);
        AccountValidator validator = factory.createValidator();
        BankDocument document = factory.createDocument();

        if (!validator.validate(accountNumber)) throw new IllegalArgumentException("Invalid account number");
        document.print();
    }

    @Override
    public void transfer(String country, String from, String to, BigDecimal amount) {
        CountryBankFactory factory = getFactoryByCountry(country);
        factory.createPaymentProcessor().processPayment(from, to, amount);
    }

    @Override
    public void reportTransaction(String country, String txId, BigDecimal amount) {
        CountryBankFactory factory = getFactoryByCountry(country);
        factory.createTaxReporter().reportTransaction(txId, amount);
    }

    private CountryBankFactory getFactoryByCountry(String country) {
        CountryBankFactory factory = factories.get(country.toLowerCase());
        if(factory == null) throw new IllegalArgumentException("Unsupported country!"); //todo implement exception
        return factory;
    }
}
