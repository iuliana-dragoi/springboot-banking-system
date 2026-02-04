package com.example.bankingsystem.account.events;

@FunctionalInterface
public interface AccountProcessor {

    public void process(Long accountId);
}
