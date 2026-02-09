package com.example.bankingsystem.transaction.service;

import com.example.bankingsystem.account.model.Account;
import com.example.bankingsystem.transaction.TransactionStatus;
import com.example.bankingsystem.transaction.config.FeeConfigurationCache;
import com.example.bankingsystem.transaction.config.TransactionIdGenerator;
import com.example.bankingsystem.transaction.model.FeeRule;
import com.example.bankingsystem.transaction.model.Transaction;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final FeeConfigurationCache feeConfigurationCache;
    private final TransactionIdGenerator transactionIdGenerator;

    public TransactionServiceImpl(FeeConfigurationCache feeConfigurationCache, TransactionIdGenerator transactionIdGenerator) {
        this.feeConfigurationCache = feeConfigurationCache;
        this.transactionIdGenerator = transactionIdGenerator;
    }

    public Optional<FeeRule> getFeeForType(String type) {
        return feeConfigurationCache.getFeeRule(type);
    }

    @Override
    public Transaction createTransaction(Account source, Account target, BigDecimal amount) {
        String transactionId = transactionIdGenerator.generatedId();
        FeeRule feeRule = feeConfigurationCache.getFeeRule("WIRE_TRANSFER")
                .orElseThrow(() -> new IllegalStateException("Fee rule missing!")); // todo implement exception

        BigDecimal fee = feeRule.amount();
        BigDecimal finalAmount = amount.add(fee);

        Transaction tx = new Transaction(source, target, finalAmount);
        tx.markStatus(TransactionStatus.CREATED);

        return tx;
    }
}
