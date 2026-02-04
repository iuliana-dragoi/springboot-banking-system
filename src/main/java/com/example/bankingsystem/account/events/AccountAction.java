package com.example.bankingsystem.account.events;

@FunctionalInterface
public interface AccountAction {

    void execute(AccountCreatedEvent event);
}
