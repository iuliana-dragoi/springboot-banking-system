package com.example.bankingsystem.account.repository.Projection;

import com.example.bankingsystem.account.model.AccountStatus;
import com.example.bankingsystem.account.model.AccountType;

import java.math.BigDecimal;

public interface AccountSimpleProjection {

    Long getId();
    String getIban();
}
