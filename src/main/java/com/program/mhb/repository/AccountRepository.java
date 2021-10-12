package com.program.mhb.repository;

import com.program.mhb.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    List<Account> getAccountsByCustomer_Id(int id);
}
