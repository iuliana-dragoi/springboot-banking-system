package com.example.bankingsystem.transaction.service;

import com.example.bankingsystem.account.model.Account;
import com.example.bankingsystem.transaction.model.Transaction;

import java.math.BigDecimal;

public interface TransactionService {
    Transaction createTransaction(Account source, Account target, BigDecimal amount);
}
