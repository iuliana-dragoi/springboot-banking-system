package com.example.bankingsystem.payment.adaptee;

public class VisaDirect {
    public boolean executeTransaction(String fromCard, String toCard, double amount) {
        System.out.println("VisaDirect: executing transaction $" + amount + " from " + fromCard + " to " + toCard);
        return true;
    }
}
