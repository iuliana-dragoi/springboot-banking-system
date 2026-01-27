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
public class AccountSearchResponse {

    private AccountStatus status;
    private String accountNumber;
    private AccountType accountType;
    private BigDecimal balance;
    private String ownerFullName;
}
