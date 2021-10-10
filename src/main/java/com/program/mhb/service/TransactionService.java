package com.program.mhb.service;

import com.program.mhb.domain.Transaction;
import com.program.mhb.dto.TransactionInsertDto;
import com.program.mhb.dto.TransactionViewDto;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionService {

    List<TransactionViewDto> getAll();

    List<TransactionViewDto> getAllByAccountId(int accountId);

    List<TransactionViewDto> getAllByAccountIdAndAfterDateTime(int accountId, LocalDateTime afterDateTime);

    List<TransactionViewDto> getAllByAccountIdAndBetweenDateTime(int accountId, LocalDateTime startDateTime, LocalDateTime endDateTime);

    void save(TransactionInsertDto transactionInsertDto);
}
