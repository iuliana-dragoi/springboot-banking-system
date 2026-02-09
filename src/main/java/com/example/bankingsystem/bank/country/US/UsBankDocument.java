package com.example.bankingsystem.bank.country.US;

import com.example.bankingsystem.bank.BankDocument;

public class UsBankDocument implements BankDocument {

    @Override
    public void print() {
        System.out.println("Printing contract in English");
    }
}
