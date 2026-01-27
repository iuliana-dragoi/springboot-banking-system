package com.example.bankingsystem.customer.model;

public class CorporateCustomer extends Customer {


    @Override
    public CustomerType getCustomerType() {
        return CustomerType.CORPORATE;
    }
}
