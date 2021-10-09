package com.program.mhb.service.impl;

import com.program.mhb.domain.Customer;
import com.program.mhb.repository.CustomerRepository;
import com.program.mhb.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> findAll() {
//        List<Customer> customers = new ArrayList<>();
//        customerRepository.findAll().forEach(customer -> customers.add());
//        return customers;
        return customerRepository.findAll();
    }
}
