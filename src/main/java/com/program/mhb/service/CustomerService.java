package com.program.mhb.service;

import com.program.mhb.domain.Customer;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CustomerService {

    @Transactional(readOnly = true)
    List<Customer> findAll();
}
