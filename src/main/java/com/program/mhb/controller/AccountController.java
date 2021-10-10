package com.program.mhb.controller;

import com.program.mhb.domain.Account;
import com.program.mhb.dto.AccountShortViewDto;
import com.program.mhb.dto.AccountViewDto;
import com.program.mhb.service.AccountService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@Log4j2
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<AccountViewDto> getAll() {
        log.info("*** List of accounts: ");
        return accountService.getAll();

    }

    @GetMapping(value = "/account/{id}")
    public AccountViewDto getById(@PathVariable("id") int id) {
        return accountService.getById(id);
    }

    @GetMapping(value = "/customer/{id}")
    public List<AccountShortViewDto> getAllByCustomerId(@PathVariable("id") int id) {
        return accountService.getAllByCustomerId(id);
    }

    @PostMapping(value = "/create")
    // TODO fara @RequestBody nu merge + CSRF disabled
    public void create(@RequestBody AccountViewDto accountViewDto) {
        accountService.saveSmart(accountViewDto);
    }

    @DeleteMapping(value = "/delete")
    // TODO ceva de genul: http://localhost:8080/accounts/delete?id=1
    public void delete(@RequestParam("id") int id) {
        accountService.deleteById(id);
    }
}
