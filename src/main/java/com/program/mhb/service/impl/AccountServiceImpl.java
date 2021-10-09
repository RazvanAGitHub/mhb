package com.program.mhb.service.impl;

import com.program.mhb.domain.Account;
import com.program.mhb.dto.AccountShortViewDto;
import com.program.mhb.dto.AccountViewDto;
import com.program.mhb.exception.NotFoundException;
import com.program.mhb.repository.AccountRepository;
import com.program.mhb.service.AccountService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<AccountViewDto> getAll() {
        List<AccountViewDto> accounts = new ArrayList<>();

        accountRepository.findAll()
                .forEach(account -> accounts
                        .add(new AccountViewDto(account.getCustomer().getId(), account.getIban(), account.getCurrency())));
        return accounts;
    }

    @Override
    public AccountViewDto getById(int id) {
        Account account;
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isPresent()) {
            account = optionalAccount.get();
        } else {
            throw new NotFoundException("Account with id: " + id + " not found.");
        }

        return new AccountViewDto(account.getCustomer().getId(), account.getIban(), account.getCurrency());
    }

    @Override
    public List<AccountShortViewDto> getAllByCustomerId(int customerId) {
        List<AccountShortViewDto> accountsByCustomerId = new ArrayList<>();

        accountRepository.findAll()
                .stream()
                .filter(account -> account.getCustomer().getId() == customerId)
                .forEach(account -> accountsByCustomerId
                        .add(new AccountShortViewDto(account.getIban(), account.getCurrency())));
        return accountsByCustomerId;
    }

    @Override
    public void save(Account account) {
        log.info("###### In Service -  preparing to save the mother fucker account");
        accountRepository.save(account);
    }
}
