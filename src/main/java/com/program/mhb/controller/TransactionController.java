package com.program.mhb.controller;

import com.program.mhb.domain.Account;

import com.program.mhb.dto.TransactionInsertDto;
import com.program.mhb.dto.TransactionViewDto;
import com.program.mhb.exception.TransactionException;
import com.program.mhb.service.AccountService;
import com.program.mhb.service.TransactionService;
import lombok.extern.log4j.Log4j2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/transactions")
@Log4j2
public class TransactionController {

    private final TransactionService transactionService;
    private final AccountService accountService;

    public TransactionController(TransactionService transactionService, AccountService accountService) {
        this.transactionService = transactionService;
        this.accountService = accountService;
    }

    @GetMapping
    public String getAll(Model model) {
        log.info("*** List of transactions: ");
        model.addAttribute("transactions", transactionService.getAll());

        return "/transactions/list-transactions";
    }

//    @GetMapping(value = "account/{id}")
//    public List<TransactionViewDto> getAllByAccountId(@PathVariable("id") int accountId) {
//        return transactionService.getAllByAccountId(accountId);
//    }

    @GetMapping(value = "account")
    public String getAllByAccountId(@RequestParam("id") int id, Model model) {
        model.addAttribute("transactions", transactionService.getAllByAccountId(id));

        return "transactions/list-transactions-by-account-id";
    }

    @GetMapping(value = "account/{id}/after-date-time/{afterDateTime}")
    public String getAllByAccountIdAndAfterDateTime(@PathVariable("id") int id, @PathVariable("afterDateTime") LocalDateTime afterDateTime, Model model) {
        model.addAttribute("transactions", transactionService.getAllByAccountIdAndAfterDateTime(id, afterDateTime));

        return "transactions/list-transactions-by-account-id";
    }

    @GetMapping(value = "account/{id}/start-date-time/{startDateTime}/end-date-time/{endDateTime}")
    public String getAllByAccountIdAndBetweenDateTime(
            @PathVariable("id") int id,
            @PathVariable("startDateTime") LocalDateTime startDateTime,
            @PathVariable("endDateTime") LocalDateTime endDateTime, Model model) throws TransactionException {
        model.addAttribute("transactions", transactionService.getAllByAccountIdAndBetweenDateTime(id, startDateTime, endDateTime));

        return "transactions/list-transactions-by-account-id";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        // create model attribute to bind form data
        TransactionViewDto transactionViewDto = new TransactionViewDto();
        List<Account> accounts = accountService.getAll();

        theModel.addAttribute("transaction", transactionViewDto);
        theModel.addAttribute("accounts", accounts);

        return "transactions/transaction-form";
    }

    @PostMapping(value = "/create")
    public String create(@ModelAttribute("transaction") TransactionInsertDto transactionInsertDto) throws TransactionException {
        transactionService.save(transactionInsertDto);

        return "redirect:/transactions";
//        return "redirect:/transactions/list-transactions-by-account-id";
    }
}
