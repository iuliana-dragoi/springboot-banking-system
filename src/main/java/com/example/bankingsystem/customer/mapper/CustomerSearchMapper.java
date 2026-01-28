package com.example.bankingsystem.customer.mapper;

import com.example.bankingsystem.customer.dto.CustomerResponse;
import com.example.bankingsystem.customer.model.Customer;

public class CustomerSearchMapper {

    public static CustomerResponse toDto(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPhone()
        );
    }
}
