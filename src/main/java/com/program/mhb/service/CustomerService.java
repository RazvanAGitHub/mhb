package com.program.mhb.service;

import com.program.mhb.domain.Customer;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface CustomerService {

    /**
     * Return the List of customers
     *
     * @return
     */
    @Transactional(readOnly = true)
    List<Customer> findAll();
}
