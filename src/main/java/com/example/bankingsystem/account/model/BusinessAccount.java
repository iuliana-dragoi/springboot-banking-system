package com.example.bankingsystem.account.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("BUSINESS")
public class BusinessAccount extends Account {

    public BusinessAccount(AccountStatus status, BigDecimal balance, Owner owner) {
        super(status, balance, owner);
    }

    @Override
    public AccountType getType() {
        return AccountType.BUSINESS;
    }
}
