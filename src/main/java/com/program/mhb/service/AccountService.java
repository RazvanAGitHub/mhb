package com.program.mhb.service;

import com.program.mhb.domain.Account;
import com.program.mhb.dto.AccountShortViewDto;
import com.program.mhb.dto.AccountViewDto;

import java.util.List;

public interface AccountService {

    List<AccountViewDto> getAll();

    List<Account> getAllBulk();

    AccountViewDto getById(int id);

    List<AccountShortViewDto> getAllByCustomerId(int customerId);

    List<Account> getAccountsByCustomer_Id(int id);

    void saveSmart(AccountViewDto accountViewDto);

    void deleteById(int id);
}
