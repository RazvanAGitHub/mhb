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

    @Transactional(readOnly = true)
    List<AccountViewDto> getAll();

    /**
     * Get all accounts...
     */
    @Transactional(readOnly = true)
    List<Account> getAllBulk();

    @Transactional
    AccountViewDto getById(@Valid int id);

    @Transactional
    List<AccountShortViewDto> getAllByCustomerId(@Valid int customerId);

    @Transactional
    List<Account> getAccountsByCustomer_Id(@Valid int id);

    @Transactional
    void saveSmart(@Valid AccountViewDto accountViewDto) throws AccountException;

    @Transactional
    void deleteById(@Valid int id);
}
