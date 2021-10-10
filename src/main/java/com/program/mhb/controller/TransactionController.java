package com.program.mhb.controller;

import com.program.mhb.domain.Transaction;
import com.program.mhb.dto.TransactionInsertDto;
import com.program.mhb.dto.TransactionViewDto;
import com.program.mhb.service.TransactionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/transactions")
@Log4j2
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<TransactionViewDto> getAll() {
        log.info("*** List of transactions: ");
        return transactionService.getAll();
    }

    @GetMapping(value = "account/{id}")
    public List<TransactionViewDto> getAllByAccountId(@PathVariable("id") int accountId) {
        return transactionService.getAllByAccountId(accountId);
    }

    @GetMapping(value = "account/{id}/date-time/{afterDateTime}")
    public List<TransactionViewDto> getAllByAccountIdAndAfterDateTime(@PathVariable("id") int id, @PathVariable("afterDateTime") LocalDateTime afterDateTime) {
        return transactionService.getAllByAccountIdAndAfterDateTime(id, afterDateTime);
    }

    @GetMapping(value = "account/{id}/start-date-time/{startDateTime}/end-date-time/{endDateTime}")
    public List<TransactionViewDto> getAllByAccountIdAndBetweenDateTime(
            @PathVariable("id") int id,
            @PathVariable("startDateTime") LocalDateTime startDateTime,
            @PathVariable("endDateTime") LocalDateTime endDateTime) {
        return transactionService.getAllByAccountIdAndBetweenDateTime(id, startDateTime, endDateTime);
    }

    @PostMapping(value = "/create")
    public void create(@RequestBody TransactionInsertDto transactionInsertDto) {
        transactionService.save(transactionInsertDto);
    }
}
