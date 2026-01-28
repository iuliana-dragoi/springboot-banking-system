package com.example.bankingsystem.account.mapper;

import com.example.bankingsystem.account.dto.AccountSearchCriteria;
import com.example.bankingsystem.account.dto.AccountSearchRequest;

public class AccountSearchMapper {

    public static AccountSearchCriteria toCriteria(AccountSearchRequest request) {
        return AccountSearchCriteria.builder()
                .status(request. getStatus())
                .accountNumber(request. getAccountNumber())
                .maxBalance(request. getMaxBalance())
                .minBalance(request. getMinBalance())
                .accountType(request. getAccountType())
                .build();
    }
}
