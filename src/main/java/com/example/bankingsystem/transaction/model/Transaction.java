package com.example.bankingsystem.transaction.model;

import com.example.bankingsystem.account.model.Account;
import com.example.bankingsystem.common.model.BaseEntity;
import com.example.bankingsystem.transaction.TransactionStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "transaction")
public class Transaction extends BaseEntity {

    @ManyToOne(optional = false)
    private Account sourceAccount;

    @ManyToOne(optional = false)
    private Account targetAccount;

    @Column(nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionStatus status;

    public Transaction(Account sourceAccount, Account targetAccount, BigDecimal amount) {

        if (sourceAccount == null || targetAccount == null) {
            throw new IllegalArgumentException("Accounts must not be null");
        }
        if (sourceAccount.equals(targetAccount)) {
            throw new IllegalArgumentException("Source and target account must differ");
        }
        if (amount == null || amount.signum() <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        this.sourceAccount = sourceAccount;
        this.targetAccount = targetAccount;
        this.amount = amount;
    }

    public void markStatus(TransactionStatus newStatus) {
        this.status = newStatus;
    }
}
