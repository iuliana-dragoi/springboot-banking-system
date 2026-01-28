package com.example.bankingsystem.account.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("CURRENT")
public class CurrentAccount extends Account {

    public CurrentAccount(AccountStatus status, BigDecimal balance, Owner owner) {
        super(status, balance, owner);
    }

    @Override
    public AccountType getType() {
        return AccountType.CURRENT;
    }
}
