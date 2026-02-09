package com.example.bankingsystem.bank.service;

import java.math.BigDecimal;

public interface BankService {
    void onboardAccount(String country, String accountNumber);
    void transfer(String country, String from, String to, BigDecimal amount);
    void reportTransaction(String country, String txId, BigDecimal amount);
}
