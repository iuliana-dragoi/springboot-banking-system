package com.example.bankingsystem.bank;

import com.example.bankingsystem.account.model.Account;
import com.example.bankingsystem.account.service.AccountService;
import com.example.bankingsystem.payment.PaymentGateway;
import com.example.bankingsystem.transaction.service.TransactionService;

import java.math.BigDecimal;

public class BankingFacade {

    private final AccountService accountService;
    private final TransactionService transactionService;
    private final PaymentGateway paymentGateway;

    public BankingFacade(AccountService accountService, TransactionService transactionService, PaymentGateway paymentGateway) {
        this.accountService = accountService;
        this.transactionService = transactionService;
        this.paymentGateway = paymentGateway;
    }

    public void makePayment(Long fromId, Long toId, BigDecimal amount) {
        Account from = accountService.searchById(fromId);
        Account to = accountService.searchById(toId);
        transactionService.createTransaction(from, to, amount);
        paymentGateway.pay(from.getAccountNumber(), to.getAccountNumber(), amount);
        System.out.println("Payment done via Facade!");
    }
}
