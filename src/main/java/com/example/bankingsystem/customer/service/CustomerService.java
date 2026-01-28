package com.example.bankingsystem.customer.service;

import com.example.bankingsystem.customer.dto.CustomerResponse;

public interface CustomerService {

    CustomerResponse searchById(Long id);
}
