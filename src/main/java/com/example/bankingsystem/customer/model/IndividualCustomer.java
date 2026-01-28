package com.example.bankingsystem.customer.model;

public class IndividualCustomer extends Customer {

    public IndividualCustomer(String firstName, String lastName, String email, String phone) {
        super(firstName, lastName, email, phone);
    }

    @Override
    public CustomerType getType() {
        return CustomerType.INDIVIDUAL;
    }
}
