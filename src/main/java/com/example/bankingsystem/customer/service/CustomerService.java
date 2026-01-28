package com.example.bankingsystem.customer.service;

import com.example.bankingsystem.customer.dto.CustomerResponse;
import com.example.bankingsystem.customer.repository.Projection.CustomerSearchProjection;
import org.springframework.data.domain.Page;
import java.awt.print.Pageable;

public interface CustomerService {

    CustomerResponse searchById(Long id);

    Page<CustomerSearchProjection> searchAll(Pageable pageable);
}
