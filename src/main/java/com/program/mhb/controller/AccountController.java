package com.program.mhb.controller;

import com.program.mhb.domain.Customer;
import com.program.mhb.dto.AccountViewDto;
import com.program.mhb.service.AccountService;
import com.program.mhb.service.CustomerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/accounts")
@Log4j2
public class AccountController {

    private final AccountService accountService;
    private final CustomerService customerService;

    public AccountController(AccountService accountService, CustomerService customerService) {
        this.accountService = accountService;
        this.customerService = customerService;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("accounts", accountService.getAll());
        return "accounts/list-accounts";
    }

    @GetMapping(value = "/account/{id}")
//    public AccountViewDto getById(@PathVariable("id") int id) {
//        return accountService.getById(id);
    public String getById(@PathVariable("id") int id, Model model) {
        model.addAttribute("accounts", accountService.getById(id));
        return "accounts/list-accounts";
    }

    @GetMapping(value = "/customer")
    public String getAllByCustomerId(@RequestParam("id") int id, Model model) {
        model.addAttribute("accounts", accountService.getAccountsByCustomer_Id(id));
        return "accounts/list-accounts-by-customer-id";
    }


    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        // create model attribute to bind form data
        AccountViewDto accountViewDto = new AccountViewDto();
        List<Customer> customers = customerService.findAll();

        theModel.addAttribute("account", accountViewDto);
        theModel.addAttribute("customers", customers);

        return "accounts/account-form";
    }

    @PostMapping(value = "/create")
    public String create(@ModelAttribute("account") AccountViewDto accountViewDto) {
        accountService.save(accountViewDto);

        return "redirect:/accounts";
    }

    @GetMapping(value = "/delete")
    public String delete(@RequestParam("id") int id) {
        accountService.deleteById(id);
        log.info("Account with id = " + id + " was deleted");

        return "redirect:/accounts";
    }

    @GetMapping(value = "/close")
    public String close(@RequestParam("id") int id) {
        accountService.closeAccountById(id);

        return "redirect:/accounts";
    }
}
