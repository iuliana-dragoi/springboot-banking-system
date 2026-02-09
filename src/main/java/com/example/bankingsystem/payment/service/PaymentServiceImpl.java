package com.example.bankingsystem.payment.service;

import com.example.bankingsystem.account.exception.AccountNotFoundException;
import com.example.bankingsystem.account.model.Account;
import com.example.bankingsystem.account.repository.AccountRepository;
import com.example.bankingsystem.transaction.TransactionStatus;
import com.example.bankingsystem.transaction.model.Transaction;
import com.example.bankingsystem.transaction.service.TransactionService;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final TransactionService transactionService;
    private final AccountRepository accountRepository;

    public PaymentServiceImpl(TransactionService transactionService, AccountRepository accountRepository) {
        this.transactionService = transactionService;
        this.accountRepository = accountRepository;
    }

    @Override
    public void processPayment(Long sourceAccountId, Long targetAccountId, BigDecimal amount) {
        Account from = accountRepository.findById(sourceAccountId).orElseThrow(() -> new AccountNotFoundException("Account not found!"));
        Account to = accountRepository.findById(targetAccountId).orElseThrow(() -> new AccountNotFoundException("Account not found!"));

        Transaction tx = transactionService.createTransaction(from, to, amount);

        from.withdraw(amount);
        to.deposit(amount);

        tx.markStatus(TransactionStatus.EXECUTED);
    }
}
