package com.program.mhb.controller;

import com.program.mhb.domain.Customer;
import com.program.mhb.service.impl.CustomerServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerServiceImpl customerService;

    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getAll() {
       List<Customer> customers = customerService.findAll();
        return customers;
    }




}
