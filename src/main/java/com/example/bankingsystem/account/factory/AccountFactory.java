package com.example.bankingsystem.account.factory;

import com.example.bankingsystem.account.model.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AccountFactory {

    public Account createAccount(AccountType type, AccountStatus status, BigDecimal balance, Owner owner) {
        return switch (type) {
            case SAVINGS -> new SavingsAccount(status, balance, owner);
            case CURRENT -> new CurrentAccount(status, balance, owner);
            case BUSINESS -> new BusinessAccount(status, balance, owner);
            case PREMIUM -> new PremiumAccount(status, balance, owner);
        };
    }
}
