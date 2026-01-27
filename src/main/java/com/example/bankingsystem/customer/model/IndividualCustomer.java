package com.example.bankingsystem.customer.model;

public class IndividualCustomer extends Customer {

    @Override
    public CustomerType getCustomerType() {
        return CustomerType.INDIVIDUAL;
    }
}
