package com.example.bankingsystem.account.mapper;

import com.example.bankingsystem.account.dto.AccountSearchRequest;
import com.example.bankingsystem.account.model.Account;
import com.example.bankingsystem.account.repository.Specification.AccountSpecification;
import org.springframework.data.jpa.domain.Specification;

public class AccountSpecificationBuilder {

    public static Specification<Account> build(AccountSearchRequest request) {
        return Specification
                .where(AccountSpecification.hasStatus(request. getStatus()))
                .and(AccountSpecification.hasType(request. getAccountType()))
                .and(AccountSpecification.hasNumber(request. getAccountNumber()))
                .and(AccountSpecification.minBalance(request. getMinBalance()))
                .and(AccountSpecification.maxBalance(request. getMaxBalance()))
                .and(AccountSpecification.accountTypeEquals(request. getAccountType()));
    }
}
