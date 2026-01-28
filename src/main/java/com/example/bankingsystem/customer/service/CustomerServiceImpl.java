package com.example.bankingsystem.customer.service;

import com.example.bankingsystem.customer.dto.CustomerResponse;
import com.example.bankingsystem.customer.exception.CustomerNotFoundException;
import com.example.bankingsystem.customer.mapper.CustomerSearchMapper;
import com.example.bankingsystem.customer.model.Customer;
import com.example.bankingsystem.customer.repository.CustomerRepository;
import com.example.bankingsystem.customer.repository.Projection.CustomerSearchProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerResponse searchById(Long id) {
        Customer result = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer not found!"));
        return CustomerSearchMapper.toDto(result);
    }

    @Override
    public Page<CustomerSearchProjection> searchAll(Pageable pageable) {
        return customerRepository.findAllBy(pageable);
    }
}
