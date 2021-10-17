package com.program.mhb.service.impl;

import com.program.mhb.domain.Account;
import com.program.mhb.domain.Customer;
import com.program.mhb.domain.Status;
import com.program.mhb.dto.AccountShortViewDto;
import com.program.mhb.dto.AccountViewDto;
import com.program.mhb.exception.AccountErrorDetails;
import com.program.mhb.exception.AccountException;
import com.program.mhb.exception.NotFoundErrorDetails;
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

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Account> getAll() {
        return accountRepository.getAllByStatus(Status.ACTIVE);//findAll();
    }

    @Override
    public AccountViewDto getById(int id) {
        Account account;
        Optional<Account> optionalAccount = accountRepository.getByStatusAndId(Status.ACTIVE, id);
        if (optionalAccount.isPresent()) {
            account = optionalAccount.get();
        } else {
            throw new NotFoundException(NotFoundErrorDetails.NOT_FOUND_ID_INVALID.getReasonPhrase(),
                    NotFoundErrorDetails.NOT_FOUND_ID_INVALID.getValue());
        }

        return new AccountViewDto(account.getCustomer().getId(), account.getIban(), account.getCurrency());
    }

    @Override
    public List<Account> getAccountsByCustomer_Id(int id) {
        return accountRepository.getAllByStatusAndAndCustomer_Id(Status.ACTIVE, id);
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
            throw new NotFoundException(NotFoundErrorDetails.NOT_FOUND_ID_INVALID.getReasonPhrase(),
                    NotFoundErrorDetails.NOT_FOUND_ID_INVALID.getValue());
        }

        account.setCustomer(customer);
        account.setIban(accountViewDto.getIban());
        account.setCurrency(accountViewDto.getCurrency());
        account.setStatus(Status.ACTIVE);

        accountRepository.save(account);
        log.info("#################### Account should be created smart now");
    }

    @Override
    public void deleteById(int id) {
        accountRepository.deleteById(id);
        log.info("#################### Account should be deleted ");
    }

    @Override
    public void closeAccountById(@Valid int id) {
        Account account;
        Optional<Account> optionalAccount = accountRepository.getByStatusAndId(Status.ACTIVE, id);
        if (optionalAccount.isPresent()) {
            account = optionalAccount.get();
            account.setStatus(Status.INACTIVE);
        } else {
            throw new NotFoundException(NotFoundErrorDetails.NOT_FOUND_ID_INVALID.getReasonPhrase(),
                    NotFoundErrorDetails.NOT_FOUND_ID_INVALID.getValue());
        }
        accountRepository.save(account);
    }
}
