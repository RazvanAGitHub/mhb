package com.program.mhb.service.impl;

import com.program.mhb.domain.Account;
import com.program.mhb.domain.Status;
import com.program.mhb.domain.Transaction;
import com.program.mhb.dto.TransactionInsertDto;
import com.program.mhb.dto.TransactionViewDto;
import com.program.mhb.exception.NotFoundErrorDetails;
import com.program.mhb.exception.NotFoundException;
import com.program.mhb.exception.TransactionErrorDetails;
import com.program.mhb.exception.TransactionException;
import com.program.mhb.repository.AccountRepository;
import com.program.mhb.repository.TransactionRepository;
import com.program.mhb.service.TransactionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public List<TransactionViewDto> getAll() {
        List<TransactionViewDto> transactions = new ArrayList<>();

        transactionRepository.getAllByAccountStatus(Status.ACTIVE)
                .forEach(transaction -> transactions.add(TransactionViewDto.builder()
                        .accountId(transaction.getAccount().getId())
                        .dateTime(transaction.getDateTime())
                        .transactionDetails(transaction.getTransactionDetails())
                        .debit(transaction.getDebit())
                        .credit(transaction.getCredit())
                        .balance(transaction.getBalance())
                        .build()));
        return transactions;
    }

    @Override
    public List<TransactionViewDto> getAllByAccountId(int accountId) {
        List<TransactionViewDto> transactions = new ArrayList<>();

        transactionRepository.getAllByAccountStatusAndAccount_Id(Status.ACTIVE, accountId)
                .forEach(transaction -> transactions.add(TransactionViewDto.builder()
                        .accountId(transaction.getAccount().getId())
                        .dateTime(transaction.getDateTime())
                        .transactionDetails(transaction.getTransactionDetails())
                        .debit(transaction.getDebit())
                        .credit(transaction.getCredit())
                        .balance(transaction.getBalance())
                        .build()));
        return transactions;
    }

    @Override
    public List<TransactionViewDto> getAllByAccountIdAndAfterDateTime(int accountId, LocalDateTime afterDateTime) {
        List<TransactionViewDto> transactions = new ArrayList<>();

        transactionRepository.getAllByAccountStatusAndAccount_IdAndDateTimeAfter(Status.ACTIVE, accountId, afterDateTime)
                .forEach(transaction -> transactions.add(TransactionViewDto.builder()
                        .accountId(transaction.getAccount().getId())
                        .dateTime(transaction.getDateTime())
                        .transactionDetails(transaction.getTransactionDetails())
                        .debit(transaction.getDebit())
                        .credit(transaction.getCredit())
                        .balance(transaction.getBalance())
                        .build()));
        return transactions;
    }

    @Override
    public List<TransactionViewDto> getAllByAccountIdAndBetweenDateTime(int accountId, LocalDateTime startDateTime, LocalDateTime endDateTime) throws TransactionException {
        List<TransactionViewDto> transactions = new ArrayList<>();

        if (startDateTime.isAfter(endDateTime)) {
            throw new TransactionException(TransactionErrorDetails.TRANSACTION_END_DATE_TIME_INVALID.getReasonPhrase(),
                    TransactionErrorDetails.TRANSACTION_END_DATE_TIME_INVALID.getValue());
        }

        transactionRepository.getAllByAccountStatusAndAccount_IdAndDateTimeIsBetween(Status.ACTIVE, accountId, startDateTime, endDateTime)
                .forEach(transaction -> transactions.add(TransactionViewDto.builder()
                        .accountId(transaction.getAccount().getId())
                        .dateTime(transaction.getDateTime())
                        .transactionDetails(transaction.getTransactionDetails())
                        .debit(transaction.getDebit())
                        .credit(transaction.getCredit())
                        .balance(transaction.getBalance())
                        .build()));
//        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@ transactions = " + transactions);
        return transactions;
    }

    @Override
    public void save(TransactionInsertDto transactionInsertDto) throws TransactionException {
        Transaction transactionSave;

        transactionSave = Transaction.builder()
                .account(getAccount(transactionInsertDto))
                .dateTime(LocalDateTime.now())
                .transactionDetails(transactionInsertDto.getTransactionDetails())
                .debit(transactionInsertDto.getDebit())
                .credit(transactionInsertDto.getCredit())
                .balance(calculateBalance(transactionInsertDto))
                .build();

        transactionRepository.save(transactionSave);
    }

    private Account getAccount(TransactionInsertDto transactionInsertDto) {
        Account account;
        Optional<Account> accountOptional = accountRepository.getByStatusAndId(Status.ACTIVE, transactionInsertDto.getAccountId());

        if (accountOptional.isPresent()) {
            account = accountOptional.get();
        } else {
            throw new NotFoundException(NotFoundErrorDetails.NOT_FOUND_ID_INVALID.getReasonPhrase(),
                    NotFoundErrorDetails.NOT_FOUND_ID_INVALID.getValue());
        }

        return account;
    }

    private long calculateBalance(TransactionInsertDto transactionInsertDto) throws TransactionException {
        long calculatedBalance = 0;
        long lastBalance = 0;
        int accountId = getAccount(transactionInsertDto).getId();

        Optional<Transaction> lastTransactionOptional = transactionRepository.getTransactionsByAccount_IdOrderByDateTimeDesc(accountId)
                .stream()
                .findFirst();
        if (lastTransactionOptional.isPresent()) {
            lastBalance = lastTransactionOptional.get().getBalance();
        }

        log.info("_________________ lastBalance = " + lastBalance);
        calculatedBalance =  lastBalance + transactionInsertDto.getDebit() + transactionInsertDto.getCredit();
        log.info("_________________ calculatedBalance = " + calculatedBalance);
        if (calculatedBalance < 0) {
            throw new TransactionException(TransactionErrorDetails.TRANSACTION_INSUFFICIENT_FOUNDS.getReasonPhrase(),
                    TransactionErrorDetails.TRANSACTION_INSUFFICIENT_FOUNDS.getValue());
        }

        return calculatedBalance;
    }
}
