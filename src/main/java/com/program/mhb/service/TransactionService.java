package com.program.mhb.service;

import com.program.mhb.dto.TransactionInsertDto;
import com.program.mhb.dto.TransactionViewDto;
import com.program.mhb.exception.TransactionException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Validated
public interface TransactionService {

    /**
     * Return the List of transactions for "active" accounts
     *
     * @return
     */
    @Transactional(readOnly = true)
    List<TransactionViewDto> getAll();

    /**
     * Return the List of transactions for an "active" account specified by an id
     *
     * @param accountId
     * @return
     */
    @Transactional(readOnly = true)
    List<TransactionViewDto> getAllByAccountId(@Valid int accountId);

    /**
     * Return the List of transactions for an "active" account specified by an id and after a specific DateTime
     *
     * @param accountId
     * @param afterDateTime
     * @return
     */
    @Transactional(readOnly = true)
    List<TransactionViewDto> getAllByAccountIdAndAfterDateTime(@Valid int accountId, @Valid LocalDateTime afterDateTime);

    /**
     * Return the List of transactions for an "active" account specified by an id and a period
     *
     * @param accountId
     * @param startDateTime
     * @param endDateTime
     * @return
     */
    @Transactional(readOnly = true)
    List<TransactionViewDto> getAllByAccountIdAndBetweenDateTime(@Valid int accountId, @Valid LocalDateTime startDateTime, @Valid LocalDateTime endDateTime)
            throws TransactionException;

    /**
     * Creating atransaction the information provided from the parameter TransactionInsertDto
     *
     * @param transactionInsertDto
     */
    @Transactional
    void save(@Valid TransactionInsertDto transactionInsertDto) throws TransactionException;
}
