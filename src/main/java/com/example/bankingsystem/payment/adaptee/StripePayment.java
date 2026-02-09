package com.example.bankingsystem.payment.adaptee;

public class StripePayment {
    public boolean makePayment(String from, String to, double amount) {
        System.out.println("StripePayment: processing $" + amount + " from " + from + " to " + to);
        return true;
    }
}
