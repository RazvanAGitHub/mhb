package com.program.mhb.service.impl;

import com.program.mhb.domain.Customer;
import com.program.mhb.repository.CustomerRepository;
import com.program.mhb.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }
}
