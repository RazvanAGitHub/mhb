package com.program.mhb.service;

import com.program.mhb.domain.Transaction;
import com.program.mhb.dto.TransactionInsertDto;
import com.program.mhb.dto.TransactionViewDto;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Validated
public interface TransactionService {

    @Transactional(readOnly = true)
    List<TransactionViewDto> getAll();

    @Transactional
    List<TransactionViewDto> getAllByAccountId(@Valid int accountId);

    @Transactional
    List<TransactionViewDto> getAllByAccountIdAndAfterDateTime(@Valid int accountId, @Valid LocalDateTime afterDateTime);

    @Transactional
    List<TransactionViewDto> getAllByAccountIdAndBetweenDateTime(@Valid int accountId, @Valid LocalDateTime startDateTime, @Valid LocalDateTime endDateTime);

    @Transactional
    void save(@Valid TransactionInsertDto transactionInsertDto);
}
