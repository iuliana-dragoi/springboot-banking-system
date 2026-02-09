package com.example.bankingsystem.payment.adaptee;

public class PayPalService {
    public boolean sendPayment(String sourceEmail, String targetEmail, double amount) {
        System.out.println("PayPalService: sending $" + amount + " from " + sourceEmail + " to " + targetEmail);
        return true;
    }
}
