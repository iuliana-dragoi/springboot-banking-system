package com.example.bankingsystem.customer.repository;

import com.example.bankingsystem.customer.model.Customer;
import com.example.bankingsystem.customer.repository.Projection.CustomerSearchProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.awt.print.Pageable;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Page<CustomerSearchProjection> findAllBy(Pageable pageable);
}
