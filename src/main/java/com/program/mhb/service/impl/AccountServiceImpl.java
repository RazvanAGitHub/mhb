package com.program.mhb.service.impl;

import com.program.mhb.domain.Account;
import com.program.mhb.domain.Customer;
import com.program.mhb.dto.AccountShortViewDto;
import com.program.mhb.dto.AccountViewDto;
import com.program.mhb.exception.AccountErrorDetails;
import com.program.mhb.exception.AccountException;
import com.program.mhb.exception.NotFoundException;
import com.program.mhb.helper.AccountValidator;
import com.program.mhb.repository.AccountRepository;
import com.program.mhb.repository.CustomerRepository;
import com.program.mhb.service.AccountService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final AccountValidator accountValidator;

    public AccountServiceImpl(AccountRepository accountRepository, CustomerRepository customerRepository, AccountValidator accountValidator) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.accountValidator = accountValidator;
    }

    @Override
    public List<AccountViewDto> getAll() {
        List<AccountViewDto> accounts = new ArrayList<>();

        accountRepository.findAll()
                .forEach(account -> accounts
                        .add(new AccountViewDto(account.getCustomer().getId(), account.getIban(), account.getCurrency())));
        return accounts;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Account> getAllBulk() {
        return accountRepository.findAll();
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

    public List<Account> getAccountsByCustomer_Id(int id) {
        return accountRepository.getAccountsByCustomer_Id(id);
    }

    @Override
    public void save(@Valid AccountViewDto accountViewDto) throws AccountException {
        log.info("#################### Trying to Save smart");
        Account account = new Account();

//        accountValidator.isAccountViewDtoValid(accountViewDto);
        Customer customer;
        Optional<Customer> customerOptional = customerRepository.findById(accountViewDto.getCustomer_id());

        if (customerOptional.isPresent()) {
            customer = customerOptional.get();
        } else {
            throw new NotFoundException("Customer with id: " + accountViewDto.getCustomer_id() + " not found.");
        }

        account.setCustomer(customer);
        account.setIban(accountViewDto.getIban());
        account.setCurrency(accountViewDto.getCurrency());

        accountRepository.save(account);
        log.info("#################### Account should be created smart now");
    }

    @Override
    public void deleteById(int id) {
        accountRepository.deleteById(id);
        log.info("#################### Account should be deleted ");
    }
}
