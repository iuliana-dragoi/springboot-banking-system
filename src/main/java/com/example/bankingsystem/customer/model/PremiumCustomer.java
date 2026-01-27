package com.example.bankingsystem.customer.model;

public class PremiumCustomer extends Customer {


    @Override
    public  CustomerType getCustomerType() {
        return CustomerType.PREMIUM;
    }
}
