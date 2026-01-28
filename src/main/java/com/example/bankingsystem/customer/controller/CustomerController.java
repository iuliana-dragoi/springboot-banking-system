package com.example.bankingsystem.customer.controller;

import com.example.bankingsystem.customer.dto.CustomerIdRequest;
import com.example.bankingsystem.customer.dto.CustomerResponse;
import com.example.bankingsystem.customer.repository.Projection.CustomerSearchProjection;
import com.example.bankingsystem.customer.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.awt.print.Pageable;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/search/{id}")
    public ResponseEntity<CustomerResponse> searchById(@RequestBody @Valid CustomerIdRequest request) {
        CustomerResponse result = customerService.searchById(request.id());
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/search/all")
    public Page<CustomerSearchProjection> searchAll(Pageable pageable) {
        return customerService.searchAll(pageable);
    }
}
