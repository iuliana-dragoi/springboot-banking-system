package com.example.bankingsystem.account.repository.Projection;

import com.example.bankingsystem.account.model.AccountStatus;
import com.example.bankingsystem.account.model.AccountType;

import java.math.BigDecimal;

public interface AccountSearchProjection {

    Long getId();
    String getIban();
    BigDecimal getBalance();
    AccountType getType();
    AccountStatus getStatus();

    String getOwnerEmail();
}
