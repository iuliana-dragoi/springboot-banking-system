package com.example.bankingsystem.customer.model;

public class PremiumCustomer extends Customer {

    public PremiumCustomer(String firstName, String lastName, String email, String phone) {
        super(firstName, lastName, email, phone);
    }

    @Override
    public  CustomerType getType() {
        return CustomerType.PREMIUM;
    }
}
