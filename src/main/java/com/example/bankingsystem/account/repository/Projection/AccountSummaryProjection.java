package com.example.bankingsystem.account.repository.Projection;

import com.example.bankingsystem.account.model.AccountStatus;
import com.example.bankingsystem.account.model.AccountType;
import java.math.BigDecimal;

public interface AccountSummaryProjection {

    Long getId();
    String getIban();
    BigDecimal getBalance();
    AccountType getType();
    AccountStatus getStatus();
}
