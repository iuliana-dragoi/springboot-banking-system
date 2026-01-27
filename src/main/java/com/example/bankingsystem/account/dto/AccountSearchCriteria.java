package com.example.bankingsystem.account.dto;

import com.example.bankingsystem.account.model.AccountStatus;
import com.example.bankingsystem.account.model.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountSearchCriteria {

    private AccountStatus status;
    private String accountNumber;
    private BigDecimal minBalance;
    private BigDecimal maxBalance;
    private AccountType accountType;
}
