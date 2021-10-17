package com.program.mhb.service;

import com.program.mhb.domain.Account;
import com.program.mhb.dto.AccountShortViewDto;
import com.program.mhb.dto.AccountViewDto;
import com.program.mhb.exception.AccountException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Validated
public interface AccountService {

    /**
     * Get all accounts...
     */
    @Transactional(readOnly = true)
    List<Account> getAll();

    @Transactional
    AccountViewDto getById(@Valid int id);

    @Transactional
    List<Account> getAccountsByCustomer_Id(@Valid int id);

    @Transactional(rollbackFor = AccountException.class)
    void save(@Valid AccountViewDto accountViewDto) throws AccountException;

    @Transactional
    void deleteById(@Valid int id);

    @Transactional
    void closeAccountById(@Valid int id);
}
