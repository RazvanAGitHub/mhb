package com.program.mhb.repository;

import com.program.mhb.domain.Status;
import com.program.mhb.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    List<Transaction> getAllByAccountStatus(Status status);

    List<Transaction> getTransactionsByAccount_Id(int accountId);
    List<Transaction> getAllByAccountStatusAndAccount_Id(Status status, int accountId);

    List<Transaction> getTransactionsByAccount_IdAndAndDateTimeAfter(int accountId, LocalDateTime afterDateTime);
    List<Transaction> getAllByAccountStatusAndAccount_IdAndDateTimeAfter(Status status, int accountId, LocalDateTime afterDateTime);

    List<Transaction> getTransactionsByAccount_IdAndDateTimeIsBetween(int accountId, LocalDateTime startDateTime, LocalDateTime endDateTime);
    List<Transaction> getAllByAccountStatusAndAccount_IdAndDateTimeIsBetween(Status status, int accountId, LocalDateTime startDateTime, LocalDateTime endDateTime);

    List<Transaction> getTransactionsByAccount_IdOrderByDateTimeDesc(int accountId);
}
