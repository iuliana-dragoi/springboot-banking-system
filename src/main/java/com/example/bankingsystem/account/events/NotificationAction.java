package com.example.bankingsystem.account.events;

@FunctionalInterface
public interface NotificationAction {

    void execute(AccountCreatedEvent event);
}
