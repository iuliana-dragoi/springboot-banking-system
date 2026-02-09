package com.example.bankingsystem.account.composite;

import com.example.bankingsystem.account.model.AccountStatus;
import com.example.bankingsystem.account.model.CurrentAccount;
import com.example.bankingsystem.account.model.Owner;
import com.example.bankingsystem.account.model.SavingsAccount;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        AccountGroup root = new AccountGroup();

        AccountGroup savings = new AccountGroup();
        savings.add(new SavingsAccount(AccountStatus.ACTIVE, BigDecimal.valueOf(100), new Owner()));
        savings.add(new SavingsAccount(AccountStatus.ACTIVE, BigDecimal.valueOf(500), new Owner()));

        AccountGroup currents = new AccountGroup();
        currents.add(new CurrentAccount(AccountStatus.ACTIVE, BigDecimal.valueOf(200), new Owner()));

        root.add(savings);
        root.add(currents);

        System.out.println(root.balance());
    }
}
