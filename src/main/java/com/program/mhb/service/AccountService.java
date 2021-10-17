package com.program.mhb.service;

import com.program.mhb.domain.Account;
import com.program.mhb.dto.AccountViewDto;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Validated
public interface AccountService {

    /**
     * Return the List of "active" accounts
     *
     * @return
     */
    @Transactional(readOnly = true)
    List<Account> getAll();

    /**
     * Return an "active" account with the given parameter index
     *
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    AccountViewDto getById(@Valid int id);

    /**
     * Return the List of "active" accounts for a specific index of a Customer
     *
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    List<Account> getAccountsByCustomer_Id(@Valid int id);

    /**
     * Creating an account with the information provided from the parameter AccountViewDto
     *
     * @param accountViewDto
     */
    @Transactional
    void save(@Valid AccountViewDto accountViewDto);

    /**
     * Closing an "active" account with the given parameter index (status of the account becomes "inactive")
     *
     * @param id
     */
    @Transactional
    void closeAccountById(@Valid int id);
}
