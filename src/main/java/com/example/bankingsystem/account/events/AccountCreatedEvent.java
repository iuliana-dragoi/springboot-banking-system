package com.example.bankingsystem.account.events;

import com.example.bankingsystem.account.model.AccountType;

public record AccountCreatedEvent(Long accountId, String ownerEmail, AccountType type) {

}
