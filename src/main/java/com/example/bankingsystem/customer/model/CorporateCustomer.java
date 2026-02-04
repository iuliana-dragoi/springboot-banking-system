package com.example.bankingsystem.customer.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CORPORATE")
public class CorporateCustomer extends Customer {

    public CorporateCustomer(String firstName, String lastName, String email, String phone) {
        super(firstName, lastName, email, phone);
    }

    @Override
    public CustomerType getType() {
        return CustomerType.CORPORATE;
    }
}
