package com.example.bankingsystem.bank.country.RO;

import com.example.bankingsystem.bank.BankDocument;

public class RoBankDocument implements BankDocument {

    @Override
    public void print() {
        System.out.println("Printing contract in Romanian");
    }
}
