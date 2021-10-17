package com.program.mhb.repository;

import com.program.mhb.domain.Account;
import com.program.mhb.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    List<Account> getAllByStatus(Status status);

    Optional<Account> getByStatusAndId(Status status, int id);

    List<Account> getAllByStatusAndAndCustomer_Id(Status status, int id);
}
