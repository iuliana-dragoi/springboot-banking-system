package com.example.bankingsystem.account.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("PREMIUM")
public class PremiumAccount extends Account {

    public PremiumAccount(AccountStatus status, BigDecimal balance, Owner owner) {
        super(status, balance, owner);
    }

    @Override
    public AccountType getType() {
        return AccountType.SAVINGS;
    }
}
