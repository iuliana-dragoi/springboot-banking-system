package com.example.bankingsystem.account.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PREMIUM")
public class PremiumAccount extends Account {

    @Override
    public AccountType getType() {
        return AccountType.SAVINGS;
    }
}
