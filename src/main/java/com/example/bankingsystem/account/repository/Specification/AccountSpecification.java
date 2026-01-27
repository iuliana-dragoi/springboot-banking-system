package com.example.bankingsystem.account.repository.Specification;

import com.example.bankingsystem.account.model.Account;
import com.example.bankingsystem.account.model.AccountStatus;
import com.example.bankingsystem.account.model.AccountType;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class AccountSpecification {

    public static Specification<Account> hasStatus(AccountStatus status) {
        return (root, query, criteria) ->
            status == null ? null : criteria.equal(root.get("status"), status);
    }

    public static Specification<Account> hasType(AccountType type) {
        return (root, query, criteria) ->
                type == null ? null : criteria.equal(root.get("type"), type);
    }

    public static Specification<Account> hasNumber(String accountNumber) {
        return (root, query, criteria) ->
                accountNumber == null ? null : criteria.equal(root.get("accountNumber"), accountNumber);
    }

    public static Specification<Account> minBalance(BigDecimal minBalance) {
        return (root, query, criteria) ->
                minBalance == null ? null : criteria.ge(root.get("balance"), minBalance);
    }

    public static Specification<Account> maxBalance(BigDecimal maxBalance) {
        return (root, query, criteria) ->
                maxBalance == null ? null : criteria.le(root.get("balance"), maxBalance);
    }

    public static Specification<Account> accountTypeEquals(AccountType accountType) {
        return (root, query, criteria) ->
                accountType == null ? null : criteria.equal(root.get("accountType"), accountType);
    }
}
