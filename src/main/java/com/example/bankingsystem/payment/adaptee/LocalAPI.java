package com.example.bankingsystem.payment.adaptee;

public class LocalAPI {
    public boolean transfer(String senderId, String receiverId, double amount) {
        System.out.println("LocalAPI: transferring $" + amount + " from " + senderId + " to " + receiverId);
        return true;
    }
}
