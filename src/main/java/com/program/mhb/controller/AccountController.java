package com.program.mhb.controller;

import com.program.mhb.domain.Customer;
import com.program.mhb.dto.AccountViewDto;
import com.program.mhb.exception.AccountException;
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

import javax.validation.Valid;
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

//    @GetMapping//(value = "/list")
////    public List<AccountViewDto> getAll() {
//    public String getAll(Model model) {
//        log.info("*** List of accounts: ");
////        return accountService.getAll();
//        model.addAttribute("accounts", accountService.getAll());
//        return "accounts/list-accounts";
//    }

    @GetMapping
    public String getAllBulk(Model model) {
        model.addAttribute("accounts", accountService.getAllBulk());
        return "accounts/list-accounts";
    }

    @GetMapping(value = "/account/{id}")
    public AccountViewDto getById(@PathVariable("id") int id) {
        return accountService.getById(id);
    }

    @GetMapping(value = "/customer/{id}")
//    public List<AccountShortViewDto> getAllByCustomerId(@PathVariable("id") int id) {
    public String getAllByCustomerId(@PathVariable("id") int id, Model model) {
//        return accountService.getAllByCustomerId(id);
//        accountService.getAllByCustomerId(id);
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
    // TODO fara @RequestBody nu merge + CSRF disabled
//    public void create(@RequestBody AccountViewDto accountViewDto) {
    public String create(@ModelAttribute("account") AccountViewDto accountViewDto) throws AccountException {
        accountService.save(accountViewDto);

        return "redirect:/accounts";
    }

    @GetMapping(value = "/delete")
    // TODO cu Thymeleaf nu merge cu DeleteMapping, cred ca m-am chinuit vreo 2 ore
    // TODO ceva de genul: http://localhost:8080/accounts/delete?id=1
//    public void delete(@RequestParam("id") int id) {
    public String delete(@RequestParam("id") int id) {
        log.info("Ready to delete");
        accountService.deleteById(id);
        log.info("After it was theoretically deleted");
        return "redirect:/accounts";
    }
}
