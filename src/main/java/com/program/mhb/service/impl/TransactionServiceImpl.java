package com.program.mhb.service.impl;

import com.program.mhb.domain.Account;
import com.program.mhb.domain.Transaction;
import com.program.mhb.dto.TransactionInsertDto;
import com.program.mhb.dto.TransactionViewDto;
import com.program.mhb.exception.NotFoundException;
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

        transactionRepository.findAll()
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

        transactionRepository.getTransactionsByAccount_Id(accountId)
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

        transactionRepository.getTransactionsByAccount_IdAndAndDateTimeAfter(accountId, afterDateTime)
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
    public List<TransactionViewDto> getAllByAccountIdAndBetweenDateTime(int accountId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        List<TransactionViewDto> transactions = new ArrayList<>();

        transactionRepository.getTransactionsByAccount_IdAndDateTimeIsBetween(accountId, startDateTime, endDateTime)
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
    public void save(TransactionInsertDto transactionInsertDto) {
        int accountId;
        Transaction transactionSave;

        Account account;
        Optional<Account> accountOptional = accountRepository.findById(transactionInsertDto.getAccountId());

        if (accountOptional.isPresent()) {
            account = accountOptional.get();
            accountId = account.getId();
        } else {
            throw new NotFoundException("Account with id: " + transactionInsertDto.getAccountId() + " not found.");
        }

        long lastBalance = 0;
        Optional<Transaction> lastTransactionOptional = transactionRepository.getTransactionsByAccount_IdOrderByDateTimeDesc(accountId)
                .stream()
                .findFirst();
        if (lastTransactionOptional.isPresent()) {
            lastBalance = lastTransactionOptional.get().getBalance();
        }

        log.info("_________________ lastBalance = " + lastBalance);
        long calculatedBalance =  lastBalance + transactionInsertDto.getDebit() + transactionInsertDto.getCredit();
        log.info("_________________ calculatedBalance = " + calculatedBalance);
        transactionSave = Transaction.builder()
                .account(account)
                .dateTime(LocalDateTime.now())
                .transactionDetails(transactionInsertDto.getTransactionDetails())
                .debit(transactionInsertDto.getDebit())
                .credit(transactionInsertDto.getCredit())
                .balance(calculatedBalance)
                .build();

        transactionRepository.save(transactionSave);
    }
// TODO de sters saveSmart()
//    @Override
//    public void saveSmart(@Valid Transaction transaction) {
//        Account account;
//        Optional<Account> accountOptional = accountRepository.findById(transaction.getAccount().getId());
//
//        if (accountOptional.isPresent()) {
//            account = accountOptional.get();
////            accountId = account.getId();
//        } else {
//            throw new NotFoundException("Account with id: " + transaction.getAccount().getId() + " not found.");
//        }
//
//        long lastBalance = 0;
//        Optional<Transaction> lastTransactionOptional = transactionRepository.getTransactionsByAccount_IdOrderByDateTimeDesc(account.getId())
//                .stream()
//                .findFirst();
//        if (lastTransactionOptional.isPresent()) {
//            lastBalance = lastTransactionOptional.get().getBalance();
//        }
//
//        log.info("_________________ lastBalance = " + lastBalance);
//        long calculatedBalance =  lastBalance + transaction.getDebit() + transaction.getCredit();
//        log.info("_________________ calculatedBalance = " + calculatedBalance);
//
//        transaction.setBalance(calculatedBalance);
//
//        transactionRepository.save(transaction);
//    }
}
