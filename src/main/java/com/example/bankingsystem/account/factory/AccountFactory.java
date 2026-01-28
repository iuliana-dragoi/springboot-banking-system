package com.example.bankingsystem.account.factory;

import com.example.bankingsystem.account.model.*;
import java.math.BigDecimal;

public class AccountFactory {

    public static Account createAccount(AccountType type, AccountStatus status, BigDecimal balance, Owner owner) {
        return switch (type) {
            case SAVINGS -> new SavingsAccount(status, balance, owner);
            case CURRENT -> new CurrentAccount(status, balance, owner);
            case BUSINESS -> new BusinessAccount(status, balance, owner);
            case PREMIUM -> new PremiumAccount(status, balance, owner);
        };
    }
}
