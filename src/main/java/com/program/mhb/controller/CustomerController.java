package com.program.mhb.controller;


import com.program.mhb.service.impl.CustomerServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerServiceImpl customerService;

    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("customers", customerService.findAll());

        return "customers/list-customers";
    }

}
